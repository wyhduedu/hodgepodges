//package com.zh.audit.confirmation.action;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.zh.audit.confirmation.htmltopdf.PdfGenerator;
//import com.zh.audit.confirmation.htmltopdf.TemplateUtils;
//import com.zh.audit.confirmation.service.CheckService;
//import com.zh.audit.confirmation.service.CommonService;
//import com.zh.audit.confirmation.service.ConfirmationService;
//import com.zh.audit.confirmation.service.ExpressService;
//import com.zh.audit.confirmation.service.FlowService;
//import com.zh.audit.confirmation.until.Constants;
//import com.zh.audit.confirmation.until.HttpClientUntil;
//import com.zh.audit.confirmation.vo.BuildExpress;
//import com.zh.audit.confirmation.vo.Confirmation;
//import com.zh.audit.confirmation.vo.ConfirmationAttach;
//import com.zh.audit.confirmation.vo.ConfirmationBank;
//import com.zh.audit.confirmation.vo.ConfirmationCheck;
//import com.zh.audit.confirmation.vo.ConfirmationCheckData;
//import com.zh.audit.confirmation.vo.ConfirmationContact;
//import com.zh.audit.confirmation.vo.ConfirmationFlow;
//import com.zh.audit.confirmation.vo.ConfirmationPress;
//import com.zh.audit.confirmation.vo.ConfirmationRemark;
//import com.zh.audit.confirmation.vo.ConfirmationReply;
//import com.zh.audit.confirmation.vo.ConfirmationSecurities;
//import com.zh.audit.confirmation.vo.ConfirmationSend;
//import com.zh.audit.confirmation.vo.ConfirmationSubmit;
//import com.zh.audit.pub.ASFuntion;
//import com.zh.framework.listener.UserSession;
//import com.zh.framework.pub.db.DBConnect;
//import com.zh.framework.pub.db.DbUtil;
//import com.zh.framework.pub.sys.UTILSysProperty;
//import com.zh.framework.pub.util.StringTools;
//
///**
// * 函证流程
// *
// * @author admin
// *
// */
//public class CheckAction extends MultiActionController {
//	ASFuntion asfFunction = new ASFuntion();
//	private static final String CORE = "函证中心";
//	private static final String ADDRESS = "浙江省杭州市江干区钱江新城新业路8号UDC时代大厦A座23楼";
//	private static final String COMPANY = "中汇会计师事务所";
//	private static final String FLOW_LIST = "/audit/confirmation/flowList.jsp";
//	private static final String UPLOAD = "/audit/confirmation/upload_result.jsp";
//
//	private static final String CODE = "code";
//	private static final String FALSE = "false";
//	private static final String RESULT = "result";
//
//	/**
//	 * 审批通过与退回，待接收,作废
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView passBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// 取得用户Session
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		int status = Integer.parseInt(request.getParameter("status"));
//		String remark = asfFunction.showNull(request.getParameter("remark"));
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			ConfirmationService cService = new ConfirmationService(conn);
//			FlowService fService = new FlowService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			if (status == 8) {
//				if (!userSession.getUserId().equals(c.getCreateUserid())) {
//					result.put("code", Constants.FAIL_CODE);
//					result.put("msg", "只能作废本人创建的函证");
//					return new ModelAndView(Constants.RESULT_VIEW, result);
//				}
//				if (c.getStatus() >= 5) {
//					result.put("code", Constants.FAIL_CODE);
//					result.put("msg", "已发函不能作废");
//					return new ModelAndView(Constants.RESULT_VIEW, result);
//				}
//			}
//			if (c.getStatus() == -9 && status == 6) {
//				String inconsistentData = asfFunction.showNull(request.getParameter("inconsistentData"));
//				if("".equals(inconsistentData)){
//					result.put("code", Constants.FAIL_CODE);
//					result.put("msg", "您填写的不符说明将自动汇总至回函统计表，请确认已填写无误");
//					return new ModelAndView(Constants.RESULT_VIEW, result);
//				}
//				List<ConfirmationCheckData> datas = JSONArray.parseArray(inconsistentData, ConfirmationCheckData.class);
//				cService.updateInconsistent(confirmationId, datas);
//			}
//			// 修改状态
//			cService.updateStatus(confirmationId, status);
//			// 添加日志
//			c.setStatus(status);
//			fService.saveFlow(userSession, c, remark);
//			// 通知
//			if (status == Constants.TWO_FU || status == Constants.NINE_FU || status == Constants.TWO) {
//				fService.sendMessageToAuditPeople(confirmationId, userSession.getUserId(), userSession.getUserName(),
//						status);
//			}
//			if (status == Constants.SIX) {
//				fService.sendMessageToConfirmationPeople(confirmationId);
//			}
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.SAVE_FAIL);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 生成函证
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView build(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		JSONObject result = new JSONObject();
//		Map<String, Object> model = new HashMap<>();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		String twoCode = asfFunction.showNull(request.getParameter("twoCode"));
//		Connection conn = null;
//		OutputStream out = null;
//		FileInputStream input = null;
//		String path = "";
//		try {
//			conn = new DBConnect().getConnect("");
//			// 判断是否已生成
//			CheckService check = new CheckService(conn);
//			List<ConfirmationAttach> attachList = check.getAttach(confirmationId, 0);
//			if (!CollectionUtils.isEmpty(attachList)) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.ATTACH_SUCCESS);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 查询数据
//			ConfirmationService service = new ConfirmationService(conn);
//			Confirmation c = service.getConfirmationById(confirmationId);
//			// 判断是否可以操作该步骤
//			if (c.getStatus() != 1) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_STATUS);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 保存二维码
//			service.updatetwoCode(confirmationId, twoCode);
//			// 拼组model
//			c.setTwoCode(twoCode);
//			if ("".equals(c.getOther())) {
//				c.setOther(null);
//			}
//			model.put("confirmation", c);
//			int type = c.getType();
//			switch (type) {
//			case 1:
//				path = "confirmation_wanglai.html";
//				List<ConfirmationContact> contanct = service.getConfirmationContact(confirmationId);
//				model.put("contact", contanct);
//				break;
//			case 2:
//				path = "confirmation_yinhang.html";
//				List<ConfirmationBank> bank = service.getConfirmationBank(confirmationId);
//				model.put("bank", bank);
//				break;
//			case 3:
//				path = "";
//				List<ConfirmationSecurities> securities = service.getSecurities(confirmationId);
//				model.put("securities", securities);
//			default:
//				break;
//			}
//			// 系统临时目录
//			String tempDir = UTILSysProperty.SysProperty.getProperty("系统临时目录");
//			// 新建一个文件
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//			String fileName = tempDir + File.separator + c.getId() + "-" + format.format(new Date()) + ".pdf";
//			// html转pdf
//			out = new FileOutputStream(fileName);
//			String content = TemplateUtils.getTranslateTemplate(path, model);
//			new PdfGenerator().generate(content, out);
//			// Html2pdf.htmlTopdf(content, new File(fileName));
//
//			// 上传文件
//			File file = new File(fileName);
//			CommonService common = new CommonService();
//			input = new FileInputStream(file);
//			MultipartFile multipartFile = new MockMultipartFile(file.getName(), input);
//			result = common.attachUpload(multipartFile, confirmationId, "");
//			if (result.getInteger(CODE) != Constants.TWO_HUNDRED) {
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 保存记录
//			JSONObject obj = result.getJSONObject("data");
//			String uuid = obj.getString("uuid");
//			ConfirmationAttach attach = new ConfirmationAttach();
//			attach.setConfirmationId(confirmationId);
//			attach.setUserId(userSession.getUserId());
//			attach.setUserName(userSession.getUserName());
//			attach.setfileName(file.getName());
//			attach.setUuid(uuid);
//			attach.setType(0);
//			attach = check.saveAttach(attach);
//			result.put("data", JSON.toJSON(attach));
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			if (input != null) {
//				input.close();
//			}
//			if (out != null) {
//				out.close();
//			}
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 删除函证文件
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView deleteAttach(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//		Long id = Long.parseLong(request.getParameter("id"));
//		Connection conn = null;
//		try {
//			if (userSession.getUserRoleNames().indexOf(CORE) < 0) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_DELETE_FOUR);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 查询数据
//			conn = new DBConnect().getConnect("");
//			CheckService check = new CheckService(conn);
//			ConfirmationAttach attach = check.getAttachById(id);
//			if (!attach.getUserId().equals(userSession.getUserId())) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_DELETE_THREE);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//
//			String url = HttpClientUntil.getUrl();
//			CommonService service = new CommonService();
//			result = service.attachRemove(attach.getUuid(), url);
//			if (result.getInteger(CODE) != Constants.TWO_HUNDRED) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.FAIL_MSG);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 删除记录
//			check.deleteAttach(id);
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 查询快递公司
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView getExpressCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String number = request.getParameter("expressNum");
//		JSONObject result = new JSONObject();
//		try {
//			ExpressService express = new ExpressService();
//			List<String> list = express.getCompany(number);
//			result.put("data", JSON.toJSON(list));
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 递函信息保存
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView submitConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView(UPLOAD);
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
//		String type = multipartRequest.getParameter("submitType");
//		Long confirmationId = Long.parseLong(multipartRequest.getParameter("confirmationId"));
//		Long receiveTime = Long.parseLong(multipartRequest.getParameter("receiveTime"));
//		String expressNum = multipartRequest.getParameter("expressNum");
//		String expressCompany = asfFunction.showNull(multipartRequest.getParameter("expressCompany"));
//		// String uuid =
//		// asfFunction.showNull(multipartRequest.getParameter("uuid"));
//
//		// MultipartFile file = multipartRequest.getFile("file");
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			// 判断是否可以操作该步骤
//			if (c.getStatus() != Constants.TWO) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_STATUS);
//				model.addObject("data", result);
//				return model;
//			}
//			// 判断重复
//			ConfirmationSubmit submit = checkService.getSumbit(confirmationId);
//			if (submit.getId() != null) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.REPEAT);
//				model.addObject("data", result);
//				return model;
//			}
//
//			CheckService check = new CheckService(conn);
//			// 判断函证是否已有
//			// List<ConfirmationAttach> attachList =
//			// check.getAttach(confirmationId, 1);
//			// if (!CollectionUtils.isEmpty(attachList)) {
//			// for (ConfirmationAttach attch : attachList) {
//			// check.deleteAttach(attch.getId());
//			// }
//			// }
//
//			// 上传文件
//			// if (file != null) {
//			// CommonService common = new CommonService();
//			// result = common.attachUpload(file, confirmationId, "");
//			// if (result.getInteger(CODE) != Constants.TWO_HUNDRED) {
//			// model.addObject("data", result);
//			// return model;
//			// }
//			// JSONObject obj = result.getJSONObject("data");
//			// String uuid = obj.getString("uuid");
//			// // 保存已盖章函证文件信息
//			// ConfirmationAttach attach = new ConfirmationAttach();
//			// attach.setConfirmationId(confirmationId);
//			// attach.setUserId(userSession.getUserId());
//			// attach.setUserName(userSession.getUserName());
//			// attach.setfileName(file.getOriginalFilename());
//			// attach.setUuid(uuid);
//			// attach.setType(1);
//			// check.saveAttach(attach);
//			// }
//			// 保存递函信息
//			submit = new ConfirmationSubmit();
//			submit.setConfirmationId(confirmationId);
//			submit.setSubmitType(Integer.parseInt(type));
//			submit.setExpressCompany(expressCompany);
//			submit.setExpressNum(expressNum);
//			submit.setReceiveTime(receiveTime);
//			checkService.saveSumbit(submit);
//
//			// 修改状态
//			FlowService fService = new FlowService(conn);
//			cService.updateStatus(confirmationId, 4);
//			// 添加日志
//			c.setStatus(4);
//			fService.saveFlow(userSession, c, "");
//
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		model.addObject("data", result);
//		return model;
//	}
//
//	/**
//	 * 生成快递面单
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView buildExpress(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		String sendUser = request.getParameter("sendUser");
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		// Long sendTime = Long.parseLong(request.getParameter("sendTime"));
//		String sendExpress = request.getParameter("sendExpress");
//		String sendPhone = request.getParameter("sendPhone");
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			BuildExpress b = new BuildExpress();
//			b.setSendUser(sendUser);
//			b.setSendExpress(sendExpress);
//			b.setSendPhone(sendPhone);
//			b.setSendAddress(ADDRESS);
//			b.setSendCompany(COMPANY);
//			b.setRecUser(c.getCompanyUser());
//			b.setRecAdress(c.getCompanyAddress());
//			b.setRecPhone(c.getCompanyMobile());
//			b.setRecCompany(c.getCompany());
//			ExpressService express = new ExpressService();
//			String obj = express.getElecOrder(b);
//			if (!"".equals(obj)) {
//				JSONObject res = JSON.parseObject(obj);
//				if (FALSE.equals(res.getString(RESULT))) {
//					result.put("code", Constants.FAIL_CODE);
//					result.put("msg", res.getString("message"));
//				} else {
//					result.put("code", Constants.SUCCESS_CODE);
//					result.put("msg", Constants.SUCCESS_MSG);
//					result.put("data", res);
//				}
//			} else {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.FAIL_MSG);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 发函信息保存
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView sendConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//		String sendUser = request.getParameter("sendUser");
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		Long sendTime = Long.parseLong(request.getParameter("sendTime"));
//		String expressNum = request.getParameter("expressNum");
//		String sendExpress = request.getParameter("sendExpress");
//		String sendPhone = request.getParameter("sendPhone");
//		String data = request.getParameter("data");
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			// 判断是否可以操作该步骤
//			if (c.getStatus() != Constants.FOUR) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_STATUS);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 判断重复
//			ConfirmationSend send = checkService.getSend(confirmationId);
//			if (send.getId() != null) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.REPEAT);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			send = new ConfirmationSend();
//			send.setConfirmationId(confirmationId);
//			send.setExpressNum(expressNum);
//			send.setSendExpress(sendExpress);
//			send.setSendPhone(sendPhone);
//			send.setSendTime(sendTime);
//			send.setSendUser(sendUser);
//			send.setWaybillData(data);
//			checkService.saveSend(send);
//
//			FlowService fService = new FlowService(conn);
//			// 修改状态
//			cService.updateStatus(confirmationId, 5);
//			// 添加日志
//
//			c.setStatus(5);
//			fService.saveFlow(userSession, c, "");
//
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 回函信息保存
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView replyConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView(UPLOAD);
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
//		Long confirmationId = Long.parseLong(multipartRequest.getParameter("confirmationId"));
//		Long replyTime = Long.parseLong(multipartRequest.getParameter("replyTime"));
//		String replyType = multipartRequest.getParameter("replyType");
//		String replyNum = multipartRequest.getParameter("expressNum");
//		String replyExpress = asfFunction.showNull(multipartRequest.getParameter("replyExpress"));
//		// String excepressUuid =
//		// asfFunction.showNull(multipartRequest.getParameter("excepressUuid"));
//		// String excepressName =
//		// asfFunction.showNull(multipartRequest.getParameter("excepressName"));
//		// 回函文件
//		// MultipartFile confirmationFile =
//		// multipartRequest.getFile("confirmationFile");
//		// 快递单扫描件
//		// MultipartFile excepressFile =
//		// multipartRequest.getFile("excepressFile");
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			// 判断是否可以操作该步骤
//			if (c.getStatus() != Constants.FIVE) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_STATUS);
//				model.addObject("data", result);
//				return model;
//			}
//			// 判断重复
//			ConfirmationReply reply = checkService.getReply(confirmationId);
//			// if (reply.getId() != null) {
//			// result.put("code", Constants.FAIL_CODE);
//			// result.put("msg", Constants.REPEAT);
//			// model.addObject("data", result);
//			// return model;
//			// }
//			// 判断函证是否已有
//			// List<ConfirmationAttach> attachList =
//			// check.getAttach(confirmationId, 2);
//			// if (!CollectionUtils.isEmpty(attachList)) {
//			// for (ConfirmationAttach attch : attachList) {
//			// check.deleteAttach(attch.getId());
//			// }
//			// }
//
//			// if (confirmationFile != null && excepressFile != null) {
//			// // 上传回函文件
//			// CommonService common = new CommonService();
//			// result = common.attachUpload(confirmationFile, confirmationId,
//			// "");
//			// if (result.getInteger(CODE) != Constants.TWO_HUNDRED) {
//			// model.addObject("data", result);
//			// return model;
//			// }
//			// JSONObject obj = result.getJSONObject("data");
//			// String confirmationUuid = obj.getString("uuid");
//			// // 上传快递单文件
//			// result = common.attachUpload(excepressFile, confirmationId, "");
//			// if (result.getInteger(CODE) != Constants.TWO_HUNDRED) {
//			// String url = HttpClientUntil.getUrl();
//			// common.attachRemove(confirmationUuid, url);
//			// model.addObject("data", result);
//			// return model;
//			// }
//			// obj = result.getJSONObject("data");
//			// String excepressUuid = obj.getString("uuid");
//			// String excepressName = excepressFile.getOriginalFilename();
//			// // 保存回函证文件信息
//			// ConfirmationAttach attach = new ConfirmationAttach();
//			// attach.setConfirmationId(confirmationId);
//			// attach.setUserId(userSession.getUserId());
//			// attach.setUserName(userSession.getUserName());
//			// attach.setfileName(confirmationFile.getOriginalFilename());
//			// attach.setUuid(confirmationUuid);
//			// attach.setType(2);
//			// checkService.saveAttach(attach);
//			// }
//
//			// 保存回函信息
//			if (reply.getId() == null) {
//				reply = new ConfirmationReply();
//				reply.setConfirmationId(confirmationId);
//				reply.setReplyTime(replyTime);
//				reply.setExpressNum(replyNum);
//				reply.setReplyType(Integer.parseInt(replyType));
//				reply.setReplyExpress(replyExpress);
//				checkService.saveReply(reply);
//			} else {
//				reply.setConfirmationId(confirmationId);
//				reply.setReplyTime(replyTime);
//				reply.setExpressNum(replyNum);
//				reply.setReplyType(Integer.parseInt(replyType));
//				reply.setReplyExpress(new String(replyExpress.getBytes("GBK"), "GBK"));
//				checkService.updateReply(reply);
//			}
//			// 修改状态
//			FlowService fService = new FlowService(conn);
//			cService.updateStatus(confirmationId, 6);
//			// 添加日志
//			c.setStatus(6);
//			fService.saveFlow(userSession, c, "");
//
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		model.addObject("data", result);
//		return model;
//	}
//
//	/**
//	 * 添加催收日志
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView pressConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		String content = request.getParameter("content");
//
//		ConfirmationPress press = new ConfirmationPress();
//		press.setConfirmationId(confirmationId);
//		press.setContent(content);
//		press.setUserId(userSession.getUserId());
//		press.setUserName(userSession.getUserName());
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			if (c.getStatus() != Constants.FIVE) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.PRESS_MSG);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//
//			CheckService checkService = new CheckService(conn);
//			press = checkService.savePress(press);
//
//			result.put("data", JSON.toJSON(press));
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 删除催收记录
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView deletePress(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		Long id = Long.parseLong(request.getParameter("id"));
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationPress press = checkService.getPressById(id);
//			if (!press.getUserId().equals(userSession.getUserId())) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_DELETE_FIVE);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			checkService.deletePress(id);
//
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 核对完结通过,异常
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView checkConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		int accountState = Integer.parseInt(request.getParameter("accountState"));
//		int replyState = Integer.parseInt(request.getParameter("replyState"));
//		int senderCompanyState = Integer.parseInt(request.getParameter("senderCompanyState"));
//		int addressState = Integer.parseInt(request.getParameter("addressState"));
//		int formatState = Integer.parseInt(request.getParameter("formatState"));
//		int sealState = Integer.parseInt(request.getParameter("sealState"));
//		String sitState = request.getParameter("situationState");
//		int situationState = Integer.parseInt((sitState == null) ? "-1" : sitState);
//		int specExplainState = Integer.parseInt(request.getParameter("specExplainState"));
//		int status = Integer.parseInt(request.getParameter("status"));
//		String account = asfFunction.showNull(request.getParameter("account"));
//		String explain = asfFunction.showNull(request.getParameter("explain"));
//
//		String checkDatas = asfFunction.showNull(request.getParameter("checkDatas"));
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationService cService = new ConfirmationService(conn);
//			Confirmation c = cService.getConfirmationById(confirmationId);
//			// 判断是否可以操作该步骤
//			if (c.getStatus() != Constants.SIX) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_STATUS);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			// 保存
//			ConfirmationCheck check = new ConfirmationCheck();
//			check.setConfirmationId(confirmationId);
//			check.setAccountState(accountState);
//			check.setReplyState(replyState);
//			check.setSenderCompanyState(senderCompanyState);
//			check.setAddressState(addressState);
//			check.setFormatState(formatState);
//			check.setSealState(sealState);
//			check.setSituationState(situationState);
//			check.setSpecExplainState(specExplainState);
//			if (!"".equals(account)) {
//				check.setAccount(new BigDecimal(account));
//			} else {
//				check.setAccount(null);
//			}
//			check.setExplain(explain);
//			ConfirmationCheck ck = checkService.getcheck(confirmationId);
//			if (ck.getId() != null) {
//				check.setId(ck.getId());
//				checkService.updataCheck(check);
//			} else {
//				checkService.saveCheck(check);
//			}
//			if (!"".equals(checkDatas)) {
//				List<ConfirmationCheckData> datas = JSONArray.parseArray(checkDatas, ConfirmationCheckData.class);
//				ConfirmationService service = new ConfirmationService(conn);
//				service.updateCheckDatas(confirmationId, datas);
//			}
//
//			FlowService fService = new FlowService(conn);
//			// 修改状态
//			cService.updateStatus(confirmationId, status);
//			// 添加日志
//			c.setStatus(status);
//			fService.saveFlow(userSession, c, "");
//			// 通知
//			if (status == Constants.NINE_FU) {
//				fService.sendMessageToAuditPeople(confirmationId, userSession.getUserId(), userSession.getUserName(),
//						status);
//			}
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 备注信息保存
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView remarkConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		Long confirmationId = Long.parseLong(request.getParameter("confirmationId"));
//		String content = request.getParameter("content");
//
//		ConfirmationRemark remark = new ConfirmationRemark();
//		remark.setConfirmationId(confirmationId);
//		remark.setContent(content);
//		remark.setUserId(userSession.getUserId());
//		remark.setUserName(userSession.getUserName());
//
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			remark = checkService.saveRemark(remark);
//
//			result.put("data", JSON.toJSON(remark));
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 备注添加附件
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView remarkUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView(UPLOAD);
//		JSONObject mapResult = new JSONObject();
//		CommonService service = new CommonService();
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//			MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
//			MultipartFile file = multipartRequest.getFile("file");
//			String fileName = file.getOriginalFilename();
//			Long remarkId = Long.parseLong(multipartRequest.getParameter("remarkId"));
//
//			ConfirmationRemark remark = checkService.getRemarkById(remarkId);
//			if (remark.getUuid() != null && !"".equals(remark.getUuid()) && !"null".equals(remark.getUuid())) {
//				mapResult.put("code", Constants.FAIL_CODE);
//				mapResult.put("msg", Constants.FILE_HAVE);
//				model.addObject("data", mapResult);
//				return model;
//			}
//			Long confirmationId = remark.getConfirmationId();
//			mapResult = service.attachUpload(file, confirmationId, "");
//			model.addObject("data", mapResult);
//			if (mapResult.getInteger("code") == 200) {
//				JSONObject obj = mapResult.getJSONObject("data");
//				checkService.updateRemark(remarkId, fileName, obj.getString("uuid"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			mapResult.put("code", Constants.FAIL_CODE);
//			mapResult.put("msg", Constants.FAIL_MSG);
//			model.addObject("data", mapResult);
//		} finally {
//			DbUtil.close(conn);
//		}
//		return model;
//	}
//
//	/**
//	 * 删除备注记录
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView deleteRemark(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		Long id = Long.parseLong(request.getParameter("id"));
//		String url = HttpClientUntil.getUrl();
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			ConfirmationRemark remark = checkService.getRemarkById(id);
//			if (!remark.getUserId().equals(userSession.getUserId())) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_DELETE_FIVE);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			CommonService service = new CommonService();
//			result = service.attachRemove(remark.getUuid(), url);
//			if (result.getInteger("code") == 200) {
//				checkService.deleteRemark(id);
//			}
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 删除备注附件
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView remarkFileRemove(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		JSONObject result = new JSONObject();
//		String id = StringTools.checkNull(request.getParameter("remarkId"));
//		String url = HttpClientUntil.getUrl();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			CheckService checkService = new CheckService(conn);
//			CommonService service = new CommonService();
//			ConfirmationRemark remark = checkService.getRemarkById(Long.parseLong(id));
//			if (!remark.getUserId().equals(userSession.getUserId())) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", Constants.NOT_DELETE_FIVE);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//			result = service.attachRemove(remark.getUuid(), url);
//			if (result.getInteger("code") == 200) {
//				checkService.updateRemark(Long.parseLong(id), "", "");
//			}
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.FAIL_MSG);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//	/**
//	 * 流程日志
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView flow(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView(FLOW_LIST);
//
//		Long confrimationId = Long.parseLong(request.getParameter("confrimationId"));
//		Connection conn = null;
//		try {
//			conn = new DBConnect().getConnect("");
//			FlowService flow = new FlowService(conn);
//			List<ConfirmationFlow> list = flow.selectFlow(confrimationId);
//			model.addObject("flow", JSON.toJSON(list));
//		} catch (Exception e) {
//			e.getStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return model;
//	}
//
//	/**
//	 * 批量提交 待提交：0；以提交：1
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView batchSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		JSONObject result = new JSONObject();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
//
//		String strList = request.getParameter("strList");
//		String[] confirmationIds = strList.split(",");
//		Connection conn = null;
//		List<Long> list = new ArrayList<>();
//		List<Long> listStatus = new ArrayList<>();
//		List<String> idList = new ArrayList<>();
//		if (confirmationIds.length <= 0) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", "请求编号为空");
//			return new ModelAndView(Constants.RESULT_VIEW, result);
//		}
//		for (int i = 0; i < confirmationIds.length; i++) {
//			idList.add(confirmationIds[i]);
//		}
//		try {
//			conn = new DBConnect().getConnect();
//			ConfirmationService confirmationService = new ConfirmationService(conn);
//			FlowService flowService = new FlowService(conn);
//			Iterator<String> iterator = idList.iterator();
//			while (iterator.hasNext()) {
//				Long id = Long.valueOf(iterator.next());
//				Confirmation confirmation = confirmationService.getConfirmationById(id);
//				if (confirmation.getStatus() != 0) {
//					listStatus.add(confirmation.getId());
//					iterator.remove();
//					continue;
//				}
//
//				if (confirmation.getType() == 1) {
//					List<Long> contactList = confirmationService.findConfContactById(id);
//					if (contactList.size() == 0) {
//						list.add(confirmation.getId());
//						iterator.remove();
//						continue;
//					}
//				}
//
//				if (confirmation.getType() == 2) {
//					List<Long> bankList = confirmationService.findConfBankById(id);
//					if (bankList.size() == 0) {
//						list.add(confirmation.getId());
//						iterator.remove();
//						continue;
//					}
//				}
//			}
//			String msg = "";
//			if (!CollectionUtils.isEmpty(listStatus)) {
//				msg = "请求编号" + JSON.toJSONString(listStatus) + "：不是待提交状态的函证。";
//			}
//
//			if (!CollectionUtils.isEmpty(list)) {
//				msg = msg + "请求编号" + JSON.toJSONString(list) + "：必填项未填写完整，请填写完整后重新提交。";
//			}
//			if (!"".equals(msg)) {
//				result.put("code", Constants.FAIL_CODE);
//				result.put("msg", msg);
//				return new ModelAndView(Constants.RESULT_VIEW, result);
//			}
//
//			for (String sid : idList) {
//				Long id = Long.valueOf(sid);
//				Confirmation confirmation = confirmationService.getConfirmationById(id);
//				confirmationService.updateStatus(id, 1);
//				flowService.saveFlow(userSession, confirmation, null);
//			}
//			result.put("code", Constants.SUCCESS_CODE);
//			result.put("msg", Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			result.put("code", Constants.FAIL_CODE);
//			result.put("msg", Constants.SAVE_FAIL);
//			e.printStackTrace();
//		} finally {
//			DbUtil.close(conn);
//		}
//		return new ModelAndView(Constants.RESULT_VIEW, result);
//	}
//
//}
