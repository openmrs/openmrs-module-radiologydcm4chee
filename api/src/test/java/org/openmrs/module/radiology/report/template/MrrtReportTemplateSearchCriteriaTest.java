/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests {@code MrrtReportTemplateSearchCriteria}.
 */
public class MrrtReportTemplateSearchCriteriaTest {
    
    
    private MrrtReportTemplateSearchCriteria mrrtReportTemplateSearchCriteria;
    
    /**
     * @see MrrtReportTemplateSearchCriteria.Builder#build()
     * @verifies create an mrrt report template search criteria instance with title if title is set
     */
    @Test
    public void build_createAnMrrtReportTemplateSearchCriteriaInstanceWithTitleIfTitleIsSet() throws Exception {
        
        String title = "Test MrrtReportTemplate";
        mrrtReportTemplateSearchCriteria = new MrrtReportTemplateSearchCriteria.Builder().withTitle(title)
                .build();
        
        assertThat(mrrtReportTemplateSearchCriteria.getTitle(), is(title));
    }
}