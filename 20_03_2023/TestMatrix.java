public class TestMatrix {
    public static void main(String[] args) {
        Matrix m = new Matrix(2, 2);
        for(int i = 0;i<m.getRows();i++){
            for (int j = 0; j < m.getColumns(); j++) {
                m.set(i, j, i + j);
            }
        }
        for(int i = 0;i<m.getRows();i++){
            for (int j = 0; j < m.getColumns(); j++) {
                System.out.print("["+m.get(i, j)+"]");
            }
            System.out.println();
        }
        
        int[][] matrice = m.mul(new int[][] {{1,2},{1,1}});
        
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print("["+matrice[i][j]+"]");
            }
            System.out.println();
        }
        
    }
}
