//package modules.verificationCode;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import core.base.BaseController;
//import core.base.DaoHelper;
//import core.base.Helper;
//import core.database.entity.Entity;
//import modules.iM.uitl.ImUtil;
//
//@Controller
//public class ModuleController extends BaseController {
//
//	private static Logger log = LogManager.getLogger(ModuleController.class);
//
//	public ModelAndView login() {
//		return new ModelAndView("module/jsp/LoginModule");
//	}
//	
//	public ModelAndView set(){
//		return jsonFormWindow("设置", "module/jsp/form/setInfoForm.jsp", "images/icons/cog.png");
//	}
//
//	public ModelAndView logins() {
//		debugOutputUrlParameters();
//		String name = getRequest().getParameter("username");
//		String pass = getRequest().getParameter("password");
//		String code = getRequest().getParameter("code");
//		
//		if(code == null|| code.trim().equals("")){
//			getRequest().setAttribute("logErr", "请检查验证码");
//			return new ModelAndView("module/jsp/LoginModule");
//		}
//		
//		String randCheckCode = (String) getSession().getAttribute("randCheckCode");
//		if(!code.equalsIgnoreCase(randCheckCode)){
//			getRequest().setAttribute("logErr", "请检查验证码");
//			return new ModelAndView("module/jsp/LoginModule");
//		}
//		
//		//在数据库中直接进行md5insert默认小写的32位
//		String md5passxiaoxie32 = ImUtil.getmd5(pass, 32);
//	
//	String sql= "SELECT COUNT(*) FROM CustomerLogin where C_NAME='"+name+"'"+" and C_PASS='"+md5passxiaoxie32+"'";
//	int count=DaoHelper.getDao().getCount(sql,null);
//		if (count>0) {
//			return new ModelAndView("module/jsp/ModuleControl");
//		}
//		return new ModelAndView("module/jsp/LoginModule");
//	}
//	
///*	public ModelAndView go() {
//		return new ModelAndView("module/jsp/ModuleControl");
//	}
//*/
//	public ModelAndView find() {
//		Collection<Module> modules = ModuleManager.getInstance().getModules();
//		List<Entity> list = new ArrayList<Entity>(modules.size());
//		for (Module module : modules) {
//			list.add(new Entity(module));
//		}
//		return outJsonEntityList(list, list.size(), null);
//	}
//
//	public ModelAndView install() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().installModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "安装模块", cr.getResponse());
//	}
//
//	public ModelAndView uninstall() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().uninstallModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "卸载模块", cr.getResponse());
//	}
//
//	public ModelAndView start() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().startModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "启动模块", cr.getResponse());
//	}
//
//	public ModelAndView stop() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().stopModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "停止模块", cr.getResponse());
//	}
//
//	public ModelAndView pause() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().pauseModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "停止模块", cr.getResponse());
//	}
//
//	public ModelAndView resume() {
//		String name = Helper.getParameters().getParameter("module");
//		ControlResponse cr = ModuleManager.getInstance().resumeModule(name, null, false);
//
//		log.info(cr.getResponse());
//		return outJsonResultMessage(cr.isSuccess(), "停止模块", cr.getResponse());
//	}
//
//	@RequestMapping(value = "module_{actionName}")
//	public ModelAndView action(@PathVariable("actionName") String actionName) {
//		// 仅允许登录用户为admin的人员访问
//		// if (AgentManager.getAgentPojo() != null &&
//		// "admin".equals(AgentManager.getAgentPojo().getAccount()))
//		return actionMethodByName(actionName);
//		// else
//		// return outString("没有权限");
//	}
//}
