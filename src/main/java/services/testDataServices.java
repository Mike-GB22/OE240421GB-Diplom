package services;

import static java.lang.Thread.sleep;

public class testDataServices {
    public static void main(String[] args) {
        //��� ������� � �������� ������ � �������
        DataServices dataServices = DataServices.getInstance();
        dataServices.printSize();

        StringBuilder result = new StringBuilder("-------------------------");
        result.append("\n * �������� ��������������");
        result.append("\n      - �� ������������ ������������:");
        result.append(" " + dataServices.userSessionService.authentication(0,"Paroll2", "1.1.1.1"));
        result.append("\n      - �� ������ ������:");
        result.append(" " + dataServices.userSessionService.authentication(1,"Paroll2", "1.1.1.1"));
        result.append("\n      - ������ ������:");
        result.append(" " + dataServices.userSessionService.authentication(1,"Paroll1", "1.1.1.1"));
        System.out.println(result);

        dataServices.printSize();
        System.out.println(dataServices.userSessionService.toString());
    }
}
