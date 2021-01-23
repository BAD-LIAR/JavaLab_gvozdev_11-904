package HTMLProcessor;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HTMLProcessor.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотаций HTMLProcessor.HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            // получаем полный путь для генерации html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // HTMLProcessor.User.class -> HTMLProcessor.User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\JavaLab\\cource\\JavaLab_gvozdev_11-904\\10.Annotations_SOURCE\\src\\main\\java\\HTMLProcessor")));

                Template template = configuration.getTemplate("Form.ftlh");


                Map<String, Object> attributes = new HashMap<>();
                Form form = new Form(element);
                attributes.put("form", form);
                template.process(attributes, writer);
                writer.close();
            } catch (IOException | TemplateException e) {
                throw new IllegalStateException(e);
            }

        }
        return true;
    }

}
