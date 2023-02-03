public class Main {
    public static void main(String[] args) {

        String encodingKeys = "H,-2";
        String text = "My Name is Sunny";

        Qwerty qwerty = new Qwerty();
        qwerty.encode(encodingKeys, text);
    }
}
