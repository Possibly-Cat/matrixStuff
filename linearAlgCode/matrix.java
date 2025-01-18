public class matrix{//Simple little class to handle basic matrix operations
    private int[][] mat;
    public matrix(int[][] theMat){
        this.mat = theMat;
    }
    public int[][] getMat(){
        return(this.mat);
    }

    public void setMat(int[][] newMat){
        this.mat = newMat;
    }

    public void add(matrix mat2){//Adds mat2 to object making call
        int[][] newMat = new int[this.mat.length][this.mat[0].length];
        if(this.mat.length == mat2.getMat().length && this.mat[0].length == mat2.getMat()[0].length){
            for(int x = 0; x < this.mat.length; x++){
                for(int y = 0; y < this.mat[0].length; y++){
                    newMat[x][y] = this.mat[x][y] + mat2.getMat()[x][y];
                }
            }
            this.setMat(newMat);
        } else{
            System.out.println("Addition failed");
        }
    }

    public void scalarMult(int scaler){ // performs scaler multiplication on an object
        int[][] newMat = new int[this.mat.length][this.mat[0].length];
        for(int x = 0; x < this.mat.length; x++){
            for(int y = 0; y < this.mat[0].length; y++){
                newMat[x][y] = scaler * this.mat[x][y];
            }
        }
        this.setMat(newMat);
    }

    public int dotProduct(matrix mat2){ //Caclulates dot product between object and mat2
        int retInt = 0;
        if(this.mat.length == mat2.getMat().length && this.mat[0].length == mat2.getMat()[0].length){
            
        }
    }

}


