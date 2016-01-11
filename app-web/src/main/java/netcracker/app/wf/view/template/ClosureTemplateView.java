package netcracker.app.wf.view.template;

import com.google.template.soy.tofu.SoyTofu;
import com.google.template.soy.tofu.SoyTofuException;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Константин on 10.01.2016.
 */
public class ClosureTemplateView extends AbstractTemplateView {
    private static final String VIEW_NOT_FOUND = "view.not_found";
    private SoyTofu compiledTemplates;
    private String module;

    @Override
    public String getContentType() {
        return "text/html;UTF-8";
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String resultHtml;
        try {
            resultHtml = compiledTemplates.newRenderer(module).setData(map).render();
        }catch (SoyTofuException e){
            resultHtml = compiledTemplates.newRenderer(VIEW_NOT_FOUND).setData(map).render();
        }

        httpServletResponse.getWriter().write(resultHtml);
    }

    public void setCompiledTemplates(SoyTofu compiledTemplates){
        this.compiledTemplates = compiledTemplates;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
