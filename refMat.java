public class refMat{
    public static void main(String[] args){
        try{ //To handle any exceptions, I've enclosed all of my test code into a single try statement. This is scuffed but functional.
            double[][] M1 = {{1, 2, 3},{4,5,6},{7,8,9}};
            double[][] M2 = {{1,0,0},{0,1,0},{0,0,1}};
            double[][] M3 = {{0,0,0},{0,0,0},{0,0,0}};
            double[][] Va = {{1}, {1},{1}, {1}};
            //These arrays represent the matrices. Each internal array is a new column, each item within an internal array is a new row.


            matrix mat1 = new matrix(M1);
            matrix I = new matrix(M2);
            matrix O = new matrix(M3);
            matrix mat4 = mat1.duplicate();
            matrix V1 = new matrix(Va);
            //Here I construct my matrices.

            mat1.prntMat();
            mat1.scalarMult(2);
            mat1.prntMat();
            I.prntMat();
            //I print the matrices to ensure they have instanciated properly

            matrix.multMat(I, mat1).prntMat();
            matrix.multMat(mat1, I).prntMat();
            matrix.multMat(mat1, O).prntMat();
            //Here I perform matrix multiplication and then print the result of that operaton

            mat1.add(I);
            mat1.prntMat();
            mat4.transpose();
            mat4.prntMat();
            //Here I perform operations and print seperately because the operations modify the object calling them and return void

            V1.projOntoVect().prntMat();
            V1.projOntoOrth().prntMat();
            //These tests of my projection functions are internally also tests of my matrix multiplication, transpose, scalar multiplication, and dot product functions


            //The following showcases the class working on a much uglier matrix
            System.out.println("\n");
            System.out.println("---------------------------------------------------");
            double[][] Vb = {{5},{12},{13},{29},{16}};
            matrix V = new matrix(Vb);
            System.out.println("The vector V is defined as: ");
            V.prntMat();
            System.out.println("The reflection matrix about the plane orthogonal to V is: ");
            V.householderFromOrth().prntMat();
        } catch(Exception e){
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

}
