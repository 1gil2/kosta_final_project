package com.kosta.springbootproject.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.springbootproject.adminservice.AdminManageClassService;
import com.kosta.springbootproject.adminservice.CertificateService;
import com.kosta.springbootproject.adminservice.ClassHistroyService;
import com.kosta.springbootproject.adminservice.ClassRoomService;
import com.kosta.springbootproject.adminservice.ClassesService;
import com.kosta.springbootproject.adminservice.EducationTimeService;
import com.kosta.springbootproject.adminservice.LectureHallService;
import com.kosta.springbootproject.adminservice.SubjectService;
import com.kosta.springbootproject.adminservice.TraineeService;
import com.kosta.springbootproject.adminservice.UserService;
import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.model.LectureHall;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Users;

@Controller
public class AdminController2 {
	
	@Autowired
	UserService userService;
	@Autowired
	CertificateService certiService;
	@Autowired
	LectureHallService lectureHallService;
	@Autowired
	ClassRoomService classRoomService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	EducationTimeService educationTimeService;
	@Autowired
	AdminManageClassService adminManageClassService;
	@Autowired
	ClassHistroyService classHistroyService;
	@Autowired
	TraineeService traineeService;
	@Autowired
	ClassesService classesService;
	
//	???????????? ????????????
	@GetMapping("/admin/manageclassmain")
	public void selectAllClassHistory(Model model) {
		List<Classes> classesResult = adminManageClassService.selectAll();
		model.addAttribute("ClassesList",classesResult);
	}
	
//	???????????? ?????? ?????? ???????????????
	@GetMapping("/admin/manageclassdetail/{classNo}")
	public ModelAndView searchClassDetail(@PathVariable Long classNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList", classhistorylist);
		mv.addObject("classes",classes);
		return mv;
	}

//	???????????? ?????? ??????????????? - ??????
	@GetMapping("/admin/manageClassDetailCommit/{classHistoryNo}")
	public ModelAndView commitManageClassDetail(@PathVariable Long classHistoryNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		//commit?????? ???????????? ?????????
		Long classNo = classHistroyService.commitManageClassDetail(classHistoryNo);
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList",classhistorylist );
		mv.addObject("classes",classes);
		return mv;
	}
	
//	???????????? ?????? ??????????????? - ??????
	@GetMapping("/admin/manageClassDetailWait/{classHistoryNo}")
	public ModelAndView waitManageClassDetail(@PathVariable Long classHistoryNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		Long classNo = classHistroyService.waitManageClassDetail(classHistoryNo);
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList",classhistorylist );
		mv.addObject("classes",classes);
		return mv;
	}
	
//	???????????? ?????? ??????????????? - ??????
	@GetMapping("/admin/manageClassDetailCancel/{classHistoryNo}")
	public ModelAndView cancelManageClassDetail(@PathVariable Long classHistoryNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		Long classNo = classHistroyService.cancelManageClassDetail(classHistoryNo);
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList",classhistorylist );
		mv.addObject("classes",classes);
		return mv;
	}
	
//	???????????? ?????? ??????????????? - ?????????
	@GetMapping("/admin/manageClassDetailUncomplete/{classHistoryNo}")
	public ModelAndView unCompleteManageClassDetail(@PathVariable Long classHistoryNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		Long classNo = classHistroyService.uncompleteManageClassDetail(classHistoryNo);
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList",classhistorylist );
		mv.addObject("classes",classes);
		return mv;
	}
	
//	???????????? ?????? ??????????????? - ??????
	@GetMapping("/admin/manageClassDetailComplete/{classHistoryNo}")
	public ModelAndView completeManageClassDetail(@PathVariable Long classHistoryNo) {
		ModelAndView mv = new ModelAndView("admin/manageClassDetail");
		Long classNo = classHistroyService.completeManageClassDetail(classHistoryNo);
		Classes classes = classesService.selectById(classNo);
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList",classhistorylist );
		mv.addObject("classes",classes);
		return mv;
	}
	

//	?????? ??????
	@GetMapping("/admin/subjectmain")
	public void selectAllSubject(Model model) {
		List<Subject> subjectlist = subjectService.selectAll();
		model.addAttribute("SubjectResult",subjectlist);
	}
	
//	?????? ?????? Get
	@GetMapping("/admin/subjectInsert")
	public String insertSubject(Model model) {
		model.addAttribute("traineeList",traineeService.selectAll());
		return "admin/subjectdetail";
	}
	
//	?????? ?????? ??? ?????? Post
	@PostMapping("/admin/subjectInsert")
	public String insertSubject(Subject subject) {

		subjectService.updateOrInsert(subject);
		return "redirect:/admin/subjectmain";
	}

//	?????? ?????? Get
	@GetMapping("/admin/subjectModify/{subjectNo}")
	public ModelAndView modifySubject(@PathVariable Long subjectNo,Model model) {
		ModelAndView mv = new ModelAndView("admin/subjectdetail");
		model.addAttribute("traineeList",traineeService.selectAll());
		Subject subject = subjectService.findSubjectBySubjectNo(subjectNo);
		mv.addObject("subject", subject);
		return mv;
	}
	
//	?????? ?????? Get
	@GetMapping("/admin/subjectDelete")
	public String deleteSubject(Long subjectNo) {
		subjectService.deleteSubject(subjectNo);
		return "redirect:/admin/subjectmain";
	}


//	???????????? ??????
	@GetMapping("/admin/educationtimemain")
	public void selectAllEducationTime(Model model) {
		List<EducationTime> result = educationTimeService.selectAll();
		model.addAttribute("EducationTimeResult",result);
	}
	
//	???????????? ?????? Get
	@GetMapping("/admin/educationtimeInsert")
	public String insertEducationTime() {
		return "admin/educationTimedetail";
	}
	
//	???????????? ?????? ??? ?????? Post
	@PostMapping("/admin/educationtimeInsert")
	public String insertEducationTime(EducationTime educationTime) {

		educationTimeService.updateOrInsert(educationTime);
		return "redirect:/admin/educationtimemain";
	}		

//	???????????? ?????? Get
	@GetMapping("/admin/educationTimeModify/{educationTimeNo}")
	public ModelAndView modifyEducationTime(@PathVariable Long educationTimeNo) {
		ModelAndView mv = new ModelAndView("admin/educationTimedetail");
		EducationTime educationTime = educationTimeService.findEducationTimeByNo(educationTimeNo);
		mv.addObject("EducationTime", educationTime);
		return mv;
	}
	
//	???????????? ??????
	@GetMapping("/admin/educationtimeDelete")
	public String deleteEducationTime(Long no) {
		educationTimeService.deleteEducationTime(no);
		return "redirect:/admin/educationtimemain";
	}	
	
	
	
//	?????? ??????
	@GetMapping("/admin/usermain")
	public void selectAllUser(Model model) {
		List<Users> result = userService.selectAll();
		model.addAttribute("UserResult",result);
	}

//	?????? ??????????????? - ????????????
	@GetMapping("/admin/userdetail/{userNo}")
	public ModelAndView showUserDetail(@PathVariable Long userNo) {
		ModelAndView mv = new ModelAndView("admin/userInfoAdmin");
		Users user = userService.findUsersByUsersNo(userNo);
		mv.addObject("user", user);
		return mv;
	}
	
//	?????? ??????????????? - ???????????? ??????
	@GetMapping("/admin/userclasshistory/{userNo}")
	public ModelAndView showUserClassHistory(@PathVariable Long userNo) {
		ModelAndView mv = new ModelAndView("admin/userClassHistory");
		List<ClassHistory> ClassHistoryList = adminManageClassService.findClassHistoryByUser(userNo);
		List<Object[]> ClassHistoryCount = adminManageClassService.selectClassHistoryCountByUser(userNo);
		Object[] countobj = ClassHistoryCount.get(0);
		Users user = userService.findUsersByUsersNo(userNo);
		mv.addObject("user", user);
		mv.addObject("classHistoryList",ClassHistoryList);
		mv.addObject("countobj",countobj);
		return mv;
	}

//	?????? "???????????????" ?????? "?????????"??? ??????
/*	@GetMapping("/admin/changetrainee")
	public void changeTrainee(@RequestParam Long userNo) {
		Users user = userService.findUsersByUsersNo(userNo);
		String a = user.getTrainee().getTraineeName();
		if(a.equals("???????????????")) {
			userService.changeToUnemp(userNo);
		}else if(a.equals("?????????")){
			userService.changeToEmp(userNo);
		}
	}
*/
	
	
//	????????? ??????	
	@GetMapping("/admin/certificatemain")
	public void selectAllCerti(Model model) {
		model.addAttribute("certiList",certiService.selectAll());
	}

//	????????? ?????? Get
	@GetMapping("/admin/certificateInsert")
	public String insertCerti() {
		return "admin/certificatedetail";
	}
	
//	????????? ?????? ??? ?????? Post
	@PostMapping("/admin/certificateInsert")
	public String insertCerti(Certificate certi) {
		certiService.updateOrInsert(certi);
		return "redirect:/admin/certificatemain";
	}
	
//	????????? ?????? Get
	@GetMapping("/admin/certificateModify/{certiNo}")
	public ModelAndView modifyCerti(@PathVariable Long certiNo) {
		ModelAndView mv = new ModelAndView("admin/certificatedetail");
		Certificate certi = certiService.findCertificateByNo(certiNo);
		mv.addObject("certi", certi);
		return mv;
	}		
	
	
//	????????? ??????
	@GetMapping("/admin/certificateDelete")
	public String deleteCerti(Long no) {
		certiService.deleteCertificate(no);
		return "redirect:/admin/certificatemain";
	}	
	
	/*
	@RequestMapping("/admin/certificatemodify")
	public String updateCerti() {
		return "/admin/certificatedetail";
	}
	*/

//	????????? ??????
	@GetMapping("/admin/lecturehallmain")
	public void selectAllLectureHall(Model model) {
		model.addAttribute("lectureHallList",lectureHallService.selectAll());
	}

//	????????? ?????? Get
	@GetMapping("/admin/lecturehallInsert")
	public String insertLectureHall() {
		return "admin/lecturehalldetail";
	}
	
//	????????? ?????? ??? ?????? Post
	@PostMapping("/admin/lecturehallInsert")
	public String insertLectureHall(LectureHall lectureHall) {
		lectureHallService.updateOrInsert(lectureHall);
		return "redirect:/admin/lecturehallmain";
	}

//	????????? ?????? Get
	@GetMapping("/admin/lectureHallModify/{lectureHallNo}")
	public ModelAndView modifyLectureHall(@PathVariable Long lectureHallNo) {
		ModelAndView mv = new ModelAndView("admin/lecturehalldetail");
		LectureHall lectureHall = lectureHallService.findLectureHallByNo(lectureHallNo);
		mv.addObject("lectureHall", lectureHall);
		return mv;
	}	
	
//	????????? ??????
	@GetMapping("/admin/lectureHallDelete")
	public String deleteLectureHall(Long no) {
		lectureHallService.deleteLectureHall(no);
		return "redirect:/admin/lecturehallmain";
	}
	
//	????????? ??????
	@RequestMapping("/admin/classroommain")
	public void selectAllClassRoom(Model model) {
		model.addAttribute("classRoomList",classRoomService.selectAll());
	}

//	????????? ?????? Get
	@GetMapping("/admin/classroomInsert")
	public String insertClassRoom(Model model) {
		List<LectureHall> lectureHallList = lectureHallService.selectAll();
		model.addAttribute("lectureHallList",lectureHallList);
		return "admin/classroomdetail";
	}

//	????????? ?????? ??? ?????? Post
	@PostMapping("/admin/classroomInsert")
	public String insertClassRoom(ClassRoom classRoom) {
		classRoomService.updateOrInsert(classRoom);
		return "redirect:/admin/classroommain";
	}
	
//	????????? ?????? Get
	@GetMapping("/admin/classRoomModify/{classRoomNo}")
	public ModelAndView modifyClassRoom(@PathVariable Long classRoomNo) {
		ModelAndView mv = new ModelAndView("admin/classroomdetail");
		ClassRoom classRoom = classRoomService.findClassRoomByNo(classRoomNo);
		List<LectureHall> lectureHallList = lectureHallService.selectAll();
		mv.addObject("lectureHallList",lectureHallList);
		mv.addObject("classRoom", classRoom);
		return mv;
	}
	
//	????????? ??????
	@GetMapping("/admin/classRoomDelete")
	public String deleteClassRoom(Long no) {
		classRoomService.deleteClassRoom(no);
		return "redirect:/admin/classroommain";
	}
	
}
