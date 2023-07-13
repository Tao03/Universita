public class Matrix {
    private int rows;
    private int columns;
    private int[][] matrix;

    public Matrix(int rows, int columns) {
        assert (rows > 0 && columns > 0) : "Inserisci valori positivi";
        this.rows = rows;
        this.columns = columns;
        matrix = new int[rows][columns];
    }

    public int get(int x, int y) {
        assert (x > 0 && y > 0) : "Inserisci valori positivi";
        return matrix[x][y];
    }

    public void set(int x, int y, int value) {
        assert (x > 0 && y > 0) : "Inserisci valori positivi";
        this.matrix[x][y] = value;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int[][] add(int[][] m1) {
        assert (m1.length==this.rows && m1[0].length == this.columns): "Dimensioni diverse";
        int[][] matrice = new int[this.rows][this.columns];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j] = this.matrix[i][j] + m1[i][j];
            }
        }
        return matrice;
    }

    public int[][] mul(int[][] m1){
        int[][] matrice = new int[this.rows][this.columns];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j] = mul(matrice[i], m1,j);
            }
        }
        return matrice;
    }

    private int mul (int[] a, int[][] b, int k){
        int s = 0;
        for (int i = 0; i < b.length; i++) {
            System.out.println("k i : "+k+" "+i+"["+b[i][k]+"]");
            s = s + a[i]*b[k][i]; 
        }
        System.out.println("--------------------------------------");
        return s;
    }
    //SBAGLIATO

    public static int mistero(int n){
        int s = 1;
        for (int i = 0; i < n; i++) {
            s = s*s + 1;
        }
        
        return s;
    }

    private int[][] mul(int[][] m1, int[][] m2){
        int[][] matrice = new int[this.rows][this.columns];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j] = mul(m1[i], m2,i);
            }
        }
        return matrice;
    }

}
