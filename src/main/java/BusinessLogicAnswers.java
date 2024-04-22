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
}
