import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //for creating pojo from URL with json
        Json2PojoFromFieldsGenerator.generatePojoFromUri("https://reqres.in/api/products", "Xz", "com.example", null);
        //for creating pojo from URL only from fields of json
        Json2PojoFromFieldsGenerator.generatePojoFromUri("https://reqres.in/api/products", "Xz", "com.example", "data[name],data[color]");
        //for creating pojo from json text
        Json2PojoFromFieldsGenerator.generatePojoFromJson("{\n" +
                "    \"page\": 1,\n" +
                "    \"per_page\": 6 }", "Xz", "com.example", null);
    }
}
