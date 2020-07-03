Simple creator from json text/response with json in body from URL to pojo (java class). 
Additionaly can create new pojo from fields(works look like JsonFilter) from existing json.
Used libs for generating pojo and Jackson JSON PropertyFilter, which selects properties of an object/list/map using a subset of the Facebook Graph API filtering syntax. 

Links: http://www.jsonschema2pojo.org/ and https://jitpack.io/p/bohnman/squiggly-filter-jackson

Examples:
 //for creating pojo from URL with json
Json2PojoFromFieldsGenerator.generatePojoFromUri("https://reqres.in/api/products", "NameOfClass", "com.example.package", null);

//for creating pojo from URL only from fields of some kind of json (will be created new pojo only with those fields, pojo are not similar to parent pojo)
Json2PojoFromFieldsGenerator.generatePojoFromUri("https://reqres.in/api/products", "NameOfClass", "com.example.package", "data[name],data[color]");

//for creating pojo from json text
Json2PojoFromFieldsGenerator.generatePojoFromJson("{\n" +
        "    \"page\": 1,\n" +
        "    \"per_page\": 6 }", "NameOfClass", "com.example", null);
        
Pojo will be in project folder 'generated'(only last example will be generated because of auto-deleting this folder).      
