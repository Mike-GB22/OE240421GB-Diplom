public class BusinessLogicAnswers {
    public static String badFirstSymbol(){
        return "�� ���������� ������ ������ �������, ��������� @";
    }

    public static String needArguments(){
        return "��� ����������� ����������";
    }

    public static String commandNotFound(){
        return "�������� �� ��������";
    }

    public static String badLogin(){
        return "ERROR BadLogin. �� ���������� ����� �/��� ������";
    }

    public static String badSession(){
        return "ERROR BadSession. ������ � ���������������� ������. �������� �� �� ����������.";
    }

    public static String okSessionEndet(){
        return "OK. ������ �������� �� ������ ����������";
    }

    /***********************************/

    public static String okExit(){
        return "OK. ����� �� �������";
    }

    /***********************************/
    public static String ok(){
        return "OK. �������� ����������";
    }
    public static String bad(){
        return "ERROR. �������� �� ����������";
    }

    /***********************************/
    public static String warnUserExited(String SID, String name) {
        return String.format("WARN. ������������ %s ����� �� ����. SID: %s", name, SID);
    }
    public static String warnUserEntered(String SID, String name) {
        return String.format("WARN. ������������ %s ����� � ���. SID: %s", name, SID);
    }

}
