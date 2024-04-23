public class BusinessLogicAnswers {
    public static String badFirstSymbol(){
        return "Не правильный первый символ запроса, ожидается @";
    }

    public static String needArguments(){
        return "Нет необходимых агрументов";
    }

    public static String commandNotFound(){
        return "Комманда не найденна";
    }

    public static String badLogin(){
        return "ERROR BadLogin. Не правильный логин и/или пароль";
    }

    public static String badSession(){
        return "ERROR BadSession. Ошибка в пользовательской сессии. Возможно ее не существует.";
    }

    public static String okSessionEndet(){
        return "OK. Сессия удаленна из списка уторизации";
    }

    /***********************************/

    public static String okExit(){
        return "OK. Выход из системы";
    }

    /***********************************/
    public static String ok(){
        return "OK. Действие выполненно";
    }
    public static String bad(){
        return "ERROR. Действие не выполненно";
    }

    /***********************************/
    public static String warnUserExited(String SID, String name) {
        return String.format("WARN. Пользователь %s вышел из чата. SID: %s", name, SID);
    }
    public static String warnUserEntered(String SID, String name) {
        return String.format("WARN. Пользователь %s зашел в чат. SID: %s", name, SID);
    }

}
