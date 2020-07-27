package app.project.library.app;


import app.project.library.exception.NoSuchOptionException;

public enum Option {

    EXIT(0, "wyjście z programu"),
    ADD_BOOK(1, "dodanie nowej książki"),
    ADD_MAGAZINE(2, "dodanie nowego magazynu"),
    PRINT_BOOKS(3, "wyświetl dostępne książki"),
    PRINT_MAGAZINE(4, "wyświetl dostępne magazyny");

    private int value;
    private String optionInfo;

    Option(int value, String optionInfo) {
        this.value = value;
        this.optionInfo = optionInfo;
    }

    public int getValue() {
        return value;
    }

    public String getOptionInfo() {
        return optionInfo;
    }

    static Option createFromInt(int userOption) throws NoSuchOptionException {
        try {
            return Option.values()[userOption];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new NoSuchOptionException("No option with id " + userOption);
        }
    }

    @Override
    public String toString() {
        return value + " - " + getOptionInfo();
    }
}
