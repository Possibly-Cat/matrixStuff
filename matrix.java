public class matrix{//Simple little class to handle basic matrix operations

    private double[][] mat;

    public matrix(double[][] theMat) throws Exception{
        int n = theMat[0].length;
        for(int x = 1; x < theMat.length; x++){
            if(theMat[x].length != n){
                throw new Exception("Colunms are not all same length");
            }
        }
        this.mat = theMat;
    }

    public double[][] getMat(){
        return(this.mat);
    }

    public void setMat(double[][] newMat) throws Exception{
        double n = newMat[0].length;
        for(int x = 1; x < newMat.length; x++){
            if(newMat[x].length != n){
                throw new Exception("Colunms are not all same length");
            }
        }
        this.mat = newMat;
    }

    public matrix duplicate() throws Exception{//A quick clone function because most of my functions are non-static. Not named clone to avoid adding an exception to an ovveride problems.
        return(new matrix(this.mat));
    }

    public boolean isVector(){
        for(int x = 1; x < this.mat.length; x++){
            if(this.mat[x].length != 1){
                return(false);
            }
        }
        return(true);
    }

    public static matrix makeI(int n) throws Exception{
        double[][] mat = new double[n][n];
        for(int x = 0; x < n; x++){
            mat[x][x] = 1;
        }
        return(new matrix(mat));
    }

    public void prntMat(){//Prints out matrix
        String retStr = "";
        for(int x = 0; x < this.mat.length; x++){
          for(int y = 0; y < this.mat[x].length;y++){
            retStr+= " " + this.mat[x][y];
            if(y != this.mat[x].length - 1){retStr+=  ", ";}
          }
          retStr += "\n";
        }
        System.out.println(retStr);
      }

      public int[] matDim(){//Returns dimensions of matrix for ease of use
        int[] retLst = {0, 0};
        retLst[0] = this.mat.length;
        retLst[1] = this.mat[0].length;
        return(retLst);
      }

    public void add(matrix mat2) throws Exception{//Adds mat2 to object making call
        double[][] newMat = new double[this.mat.length][this.mat[0].length];
        if(this.mat.length == mat2.getMat().length && this.mat[0].length == mat2.getMat()[0].length){
            for(int x = 0; x < this.mat.length; x++){
                for(int y = 0; y < this.mat[0].length; y++){
                    newMat[x][y] = this.mat[x][y] + mat2.getMat()[x][y];
                }
            }
            this.setMat(newMat);
        } else{
            throw new Exception("Adding matrices with different dimensions");
        }
    }

    public void scalarMult(double scaler) throws Exception{ // performs scaler multiplication on an object
        double[][] newMat = new double[this.mat.length][this.mat[0].length];
        for(int x = 0; x < this.mat.length; x++){
            for(int y = 0; y < this.mat[0].length; y++){
                newMat[x][y] = scaler * this.mat[x][y];
            }
        }
        this.setMat(newMat);
    }

    public double dotProduct(matrix mat2) throws Exception{ //Caclulates dot product between two vectors. Returns an exception for non-vector(s) or vectors with non-matching dimensions
        if(!this.isVector()|| !mat2.isVector()){
            throw new Exception("Trying to dot product non-vector");
        }
        if(this.mat.length != mat2.getMat().length) {
            throw new Exception("Vectors have different dimensions");
        }

        double retInt = 0;
        for(int x = 0; x < this.matDim()[0]; x++){
            retInt += (this.mat[x][0] * mat2.getMat()[x][0]);
        }
        return(retInt);
    }
    
      public static boolean canMult(matrix M1, matrix M2){//Checks that the two matrices have the correct dimensions to be multiplied with each other
        if(M1.matDim()[1] == M2.matDim()[0]){
          return(true);
        }else{
          return(false);
          }
        }

      public static double thisInd(matrix left, matrix right, int leftRowInd, int rightColInd){//Calculates a specific value of a matrix multiplication
        double retSum = 0;
        for(int n = 0; n < right.getMat().length; n++){
          retSum += left.getMat()[leftRowInd][n] * right.getMat()[n][rightColInd];
        }
        return(retSum);
      }
    
      public static matrix multMat(matrix left, matrix right) throws Exception{// overloaded/intermediate function which either continues the matrix multiplication or throws an exception if dimensions are invalid
        if(canMult(left, right)){//This function only exists to make better looking code :)
          return(multMat(left, right, 0));
        } else{
          throw new Exception("Matrix Multiplication not valid for matrices of these dimensions");
        }
      }

      public static matrix multMat(matrix left, matrix right, int n) throws Exception{//Called to multiply matrices once their dimensions are confirmed to be valid. n is only here for overloading.
        double[][] retInt = new double[left.matDim()[0]][right.matDim()[1]];
        for(int row = 0; row < left.matDim()[0]; row++){
          for(int col = 0; col < right.matDim()[1]; col++){
            retInt[row][col] = thisInd(left, right, row, col);
          }
        }
        return(new matrix(retInt));
      }

      public void transpose() throws Exception{ //Turns a matrix into its transpose
        double[][] newMat = new double[this.mat[0].length][this.mat.length];
        for(int y = 0; y < this.mat[0].length; y++){
            for(int x = 0; x < this.mat.length; x++){
                newMat[y][x] = this.mat[x][y];
            }
        }
        this.setMat(newMat);
      }

      public matrix projOntoVect() throws Exception{ //Creates a new matrix representing projection onto the calling vector
        if(!this.isVector()){
            throw new Exception("Trying to project onto non-vector");
        }
        double scalar = (1 / (this.dotProduct(this)));
        matrix trans = this.duplicate();
        trans.transpose();
        matrix ret = multMat(this, trans);
        ret.scalarMult(scalar);
        return(ret);
      }

      public matrix projOntoOrth() throws Exception{//Creates projection vector onto the object orthogonal to the calling object
        matrix I = makeI(this.mat.length);
        matrix projInit = this.projOntoVect();
        projInit.scalarMult(-1);
        I.add(projInit);
        return(I);
      }

      public matrix householderFromOrth() throws Exception{
        matrix I = makeI(this.mat.length);
        matrix projInit = this.projOntoVect();
        projInit.scalarMult(-2);
        I.add(projInit);
        return(I);
      }

}


