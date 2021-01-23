package HTMLProcessor;

import javax.lang.model.element.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Form {

    public String action;
    public String method;
    public List<Field> fieldList = new LinkedList<>();

    public class Field {
        public String type;
        public String name;
        public String placeholder;

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public Field(String type, String name, String placeholder) {
            this.type = type;
            this.name = name;
            this.placeholder = placeholder;
        }
    }

    public Form(Element element) throws IOException {

//        FileWriter fileWriter = new FileWriter(new File("D:\\JavaLab\\cource\\JavaLab_gvozdev_11-904\\10.Annotations_SOURCE\\src\\main\\java\\log.txt"));
        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
        action = htmlForm.action();
        method = htmlForm.method();
//        fileWriter.write("action: " + action + "         " + "mathod: " + method + "\n");
        List<? extends Element> list = element.getEnclosedElements();
        for (Element element1 : list){
            HtmlInput htmlInput = element1.getAnnotation(HtmlInput.class);
            if(htmlInput == null){
                continue;
            }
            Field field = new Field(htmlInput.type(), htmlInput.name(), htmlInput.placeholder());
//            fileWriter.write("type: " + field.type + "         " + "name: " + field.name + "         " + "placeholder: " + field.placeholder + "\n");
//            fileWriter.flush();
            fieldList.add(field);
        }
    }


    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }
}
