package com.tang.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tang.entity.User;
import com.tang.util.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login" ,method=RequestMethod.POST)
	public String login(User currUser,HttpSession session, HttpServletRequest request,RedirectAttributes redirectAttributes){
		String code = (String) session.getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");

		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
            redirectAttributes.addFlashAttribute("message","验证码错误！");
			return "redirect:/";
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(),currUser.getPassword());
		token.setRememberMe(true);
		try {
			user.login(token);
			//return "redirect:/user/list";
            return "success";
		}catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message","用户名或密码错误！");
			token.clear();
			return "redirect:/";
		}

    //    } catch ( UnknownAccountException uae ) {
    //    } catch ( IncorrectCredentialsException ice ) {
    //    } catch ( LockedAccountException lae ) {
    //    } catch ( ExcessiveAttemptsException eae ) {
    //    } catch ( AuthenticationException ae ) {
    //        //unexpected error?
    //    }

        }

	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}
}
