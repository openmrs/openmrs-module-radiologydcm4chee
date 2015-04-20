/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, 
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can 
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under 
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS 
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.Study;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RadiologyDashboardController implements Controller {
	
	Log log = LogFactory.getLog(getClass());
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        IOException {
		String patientId = request.getParameter("patientId");
		return ordersTable(patientId);
	}
	
	ModelAndView ordersTable(String patientId) {
		String route = "/module/radiology/portlets/RadiologyDashboardTab";
		ModelAndView mav = new ModelAndView(route);
		
		PatientService patientService = Context.getPatientService();
		Patient patient = patientService.getPatient(Integer.valueOf(patientId));
		List<Study> studyList = Context.getService(RadiologyService.class).getStudiesByPatient(patient);
		
		List<String> statuses = new Vector<String>();
		List<String> priorities = new Vector<String>();
		List<String> schedulers = new Vector<String>();
		List<String> performings = new Vector<String>();
		List<String> readings = new Vector<String>();
		List<String> mwlStatuses = new Vector<String>();
		for (Study study : studyList) {
			if (study != null) {
				statuses.add(study.getStatus(Context.getAuthenticatedUser()));
				priorities.add(study.getPriority().getDisplayName());
				schedulers.add(study.scheduler());
				performings.add(study.performing());
				readings.add(study.reading());
				mwlStatuses.add(study.getMwlStatus().getDisplayName());
			}
		}
		
		mav.addObject(studyList);
		mav.addObject("statuses", statuses);
		mav.addObject("priorities", priorities);
		mav.addObject("schedulers", schedulers);
		mav.addObject("performings", performings);
		mav.addObject("readings", readings);
		//if(Context.getAuthenticatedUser().hasRole(Roles.ReadingPhysician, true)){
		mav.addObject("obsId", "&obsId");
		//}
		mav.addObject("mwlStatuses", mwlStatuses);
		mav.addObject("patientId", patientId);
		
		return mav;
	}
}
