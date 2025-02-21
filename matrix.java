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

  public matrix rowSwap(int row1, int row2){
    double[] tempRow1 = this.mat[row1].clone();
    this.mat[row1] = this.mat[row2];
    this.mat[row2] = tempRow1;
    return(this);
  }

  public matrix rowMult(int row, double mult){
    for(int x = 0; x < this.mat[row].length; x++){
      this.mat[row][x] = this.mat[row][x] * mult;
    }
    return(this);
  }

  public matrix rowAdd(int adder, int addTo, double rowMult){
    for(int x = 0; x < this.mat[0].length; x++){
      this.mat[addTo][x] = this.mat[addTo][x] + (rowMult * this.mat[adder][x]);
    }
    return(this);
  }

  public static int nOfList(int[] list, int n, int notThis){//Simple helper function for RREF(). Finds the nth non 'notThis' entry of a list
    int adder = 0;
    for(int z:list){
      if(z != notThis){
        adder++;
        if(adder == n){return(z);}
      }
    }
    return(-1);
  }

  public static int nIndexOfList(double[] list, int n, int notThis){//Simple helper function for RREF(). Finds the index of the nth non 'notThis' entry of a list
    int adder = 0;
    for(int x = 0; x < list.length; x++){
      if(list[x] != notThis){
        adder++;
        if(adder == n){return(x);}
      }
    }
    return(10000000);
  }

  public static double nOfRow(double[] list, int n){//Simple helper function for RREF(). Finds the nth non 0 entry of a row. Takes doubles as opposed to nOfList which takes ints
    int adder = 0;
    for(double z:list){
      if(z != 0){
        adder++;
        if(adder == n){return(z);}
      }
    }
    return(-1);
  }

  public void makeRowPivotsOne(){//Simple helper function for RREF(). Makes the row pivot of every row 1.
    for(int y = 0; y < this.mat.length; y++){
      this.rowMult(y, 1 / nOfRow(this.mat[y], 1));
    }
  }

  public void rowPivotOrderer(){//Uses quick sort to order the rows of a matrix into RREF order
    int[] pivotIndeces = new int[this.mat.length];
    for(int y = 0; y < this.mat.length; y++){
      pivotIndeces[y] = nIndexOfList(this.mat[y], 1, 0);
    }
    Quick.quickSort(pivotIndeces, this.mat);
  }

  public matrix RREF(){//Program to RREF any matrix using elementery row operations. Will place zero rows not at bottom for matrices with >= 10000000 rows
    int yInc = 0;
    int rowOn = 0;
    int[] rowLst = new int[this.mat.length];
    for(int z = 0; z < this.mat.length; z++){
      rowLst[z] = z;
    }
    for(int x = 0; x < this.mat[0].length; x++){
      yInc = nOfList(rowLst, 1,  -1);
      rowOn = 1;
      while(this.mat[yInc][x] == 0){
        rowOn++;
        yInc = nOfList(rowLst, rowOn, -1);
        if(yInc == -1 && x == this.mat[0].length - 1){
          this.makeRowPivotsOne();
          this.rowPivotOrderer();
          return(this);
        }
        if(yInc == -1){
          x += 1;
          yInc = nOfList(rowLst, 1, -1);
          rowOn = 1;
        }
      }
      for(int y = 0; y < this.mat.length; y++){
        if(y != yInc){
          double mult = -1 * (this.mat[y][x] / this.mat[yInc][x]);
          this.rowAdd(yInc, y, mult);
        }
      }
      rowLst[yInc] = -1;
      if (nOfList(rowLst, 1, -1) == -1){
        this.makeRowPivotsOne();
        this.rowPivotOrderer();
        return(this);
      }
    }
    this.makeRowPivotsOne();
    this.rowPivotOrderer();
    return(this);
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
          retStr+= " " + String.format("%.3g", this.mat[x][y]);
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

    public matrix projOntoOrth() throws Exception{//Creates projection matrix onto the object orthogonal to the calling object
      matrix I = makeI(this.mat.length);
      matrix projInit = this.projOntoVect();
      projInit.scalarMult(-1);
      I.add(projInit);
      return(I);
    }

    public matrix householderFromOrth() throws Exception{//Creates reflection matrix about the plane orthogonal to the calling object
      matrix I = makeI(this.mat.length);
      matrix projInit = this.projOntoVect();
      projInit.scalarMult(-2);
      I.add(projInit);
      return(I);
    }

  public double determ() throws Exception{ //Returns the determinant of a square matrix
    if(this.mat.length != this.mat[0].length){
      throw new Exception("Can't take determinant of non-square matrix");
    }
    if(this.mat.length == 2 && this.mat[0].length == 2){
      return((this.mat[0][0] * this.mat[1][1]) - (this.mat[1][0] * this.mat[0][1]));
    }
    int sign = 1;
    int retInt = 0;
    int matX = 0;
    int matY = 0;
    double[][] tempMat = new double[this.mat.length - 1][this.mat[0].length - 1];
    for(int x = 0; x < this.mat.length; x++){
      tempMat = new double[this.mat.length - 1][this.mat[0].length - 1];
      matX = 0;
      matY = 0;
      for(int indX = 0; indX < this.mat[0].length; indX++){
        for(int y = 1; y < this.mat.length; y++){
          if(indX != x){
            tempMat[matY][matX] = this.mat[y][indX];
            matX++;
            if(matX >= tempMat.length){
              matY++;
              matX = 0;
            }
          }
        }
      }
      matrix myMat = new matrix(tempMat);
      retInt += sign * this.mat[0][x] * myMat.determ();
      sign = sign * -1;
    }
    return(retInt);
  }

}
