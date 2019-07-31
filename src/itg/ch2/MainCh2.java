package itg.ch2;


import itg.ch1.Persion;

public class MainCh2 {

    public static void main(String[] args) {
        PersionDAO persionDAO = new PersionDAO();

        Persion result =  persionDAO.get(1);
        System.out.print(result);
    }
}
