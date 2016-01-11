package netcracker.app.wf.view.template;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Константин on 10.01.2016.
 */
public class ClosureTemplateViewResolver extends AbstractTemplateViewResolver {
    private Resource templateLocation;
    private SoyTofu compiledTemplates;

    public ClosureTemplateViewResolver() {
        super();
        setExposeSpringMacroHelpers(false);
    }

    public void setTemplateLocation(Resource templateLocation) {
        this.templateLocation = templateLocation;
    }

    @Override
    protected void initApplicationContext() {
        super.initApplicationContext();
        if(compiledTemplates == null){
            initTemplates();
        }
    }

    private void initTemplates() {
        List<File> soyTemplateFiles = new ArrayList<>();
        try {
            File templateFolder = templateLocation.getFile();
            if(templateFolder.isDirectory()){
                initSoyTemplateFiles(soyTemplateFiles, templateFolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SoyFileSet.Builder fileSetBuilder = SoyFileSet.builder();

        for (File file :soyTemplateFiles) {
            fileSetBuilder.add(file);
        }
        compiledTemplates = fileSetBuilder.build().compileToTofu();

    }

    private void initSoyTemplateFiles(List<File> soyTemplateFiles, File rootFolder) {
        File[] files = rootFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".soy");
            }
        });
        if(files != null){
            for (File file :files) {
                if(file.isDirectory()){
                    initSoyTemplateFiles(soyTemplateFiles,file);
                }else if(file.isFile()){
                    soyTemplateFiles.add(file);
                }
            }

        }
    }

    @Override
    protected Class<?> getViewClass() {
        return ClosureTemplateView.class;
    }

    protected AbstractUrlBasedView buildView(String viewName)throws Exception{
        ClosureTemplateView closureTemplateView = (ClosureTemplateView)super.buildView(viewName);
        closureTemplateView.setCompiledTemplates(compiledTemplates);
        closureTemplateView.setModule(viewName);
        return closureTemplateView;
    }
}
