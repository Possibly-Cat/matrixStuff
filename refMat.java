public class refMat{
    public static void main(String[] args){
        try{ //To handle any exceptions, I've enclosed all of my test code into a single try statement. This is scuffed but functional.
            double[][] M1 = {{1, 2, 3},{4,5,6},{7,8,9}};
            double[][] M2 = {{1,0,0},{0,1,0},{0,0,1}};
            double[][] M3 = {{0,0,0},{0,0,0},{0,0,0}};
            double[][] Va = {{1}, {1},{1}, {1}};
            matrix mat1 = new matrix(M1);
            matrix I = new matrix(M2);
            matrix O = new matrix(M3);
            matrix mat4 = mat1.duplicate();
            matrix V1 = new matrix(Va);
            mat1.prntMat();
            mat1.scalarMult(2);
            mat1.prntMat();
            I.prntMat();
            matrix.multMat(I, mat1).prntMat();
            matrix.multMat(mat1, I).prntMat();
            matrix.multMat(mat1, O).prntMat();
            mat1.add(I);
            mat1.prntMat();
            mat4.transpose();
            mat4.prntMat();
            V1.projOntoVect().prntMat();
            V1.projOntoOrth().prntMat();

            // Finding a householder matrix for some V:
            System.out.printLn("")
        } catch(Exception e){
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

}
