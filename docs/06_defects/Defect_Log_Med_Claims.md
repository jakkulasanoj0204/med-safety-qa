\# Defect Log – Medical Claims Validation Portal (Phase 1)



This document tracks defects identified during testing of the Medical Claims Validation Portal.



---



\## Defect Summary Table



| Defect ID | Title                                            | Module          | Severity | Priority | Status   |

|-----------|--------------------------------------------------|-----------------|----------|----------|----------|

| DEF-001   | Mandatory field error not shown for Claim Amount | Claim Creation  | High     | High     | Open     |

| DEF-002   | Invalid diagnosis code accepted without error    | Validation      | Critical | High     | Open     |

| DEF-003   | Claim saved even when validation errors present  | Save Operation  | Critical | High     | Open     |

| DEF-004   | Incorrect error message text for missing DoB     | Claim Creation  | Medium   | Medium   | Open     |



---



\## Detailed Defects



\### DEF-001 – Mandatory field error not shown for Claim Amount



\- \*\*Module:\*\* Claim Creation  

\- \*\*Severity:\*\* High  

\- \*\*Priority:\*\* High  

\- \*\*Environment:\*\* QA  

\- \*\*Precondition:\*\* User is logged into the portal and on the Create Claim screen.  



\*\*Steps to Reproduce:\*\*

1\. Open the Create Claim page.

2\. Enter valid Patient Name, Date of Birth, Date of Service.

3\. Enter at least one valid Diagnosis Code.

4\. Enter at least one valid Procedure Code.

5\. Leave \*\*Claim Amount\*\* field blank.

6\. Click on \*\*Validate\*\*.



\*\*Expected Result:\*\*  

An error message should be displayed: “Claim Amount is required”.



\*\*Actual Result:\*\*  

No error message is shown for Claim Amount, and the system shows “Validation successful”.



\*\*Status:\*\* Open  

\*\*Reported By:\*\* QA  

\*\*Date Reported:\*\* \_<add date>\_  



---



\### DEF-002 – Invalid diagnosis code accepted without error



\- \*\*Module:\*\* Validation  

\- \*\*Severity:\*\* Critical  

\- \*\*Priority:\*\* High  

\- \*\*Environment:\*\* QA  



\*\*Steps to Reproduce:\*\*

1\. Open the Create Claim page.

2\. Enter all mandatory fields with valid data.

3\. In Diagnosis Code field, enter `112.34` (starts with a digit).

4\. Click \*\*Validate\*\*.



\*\*Expected Result:\*\*  

System should display: “Invalid diagnosis code format for 112.34”.



\*\*Actual Result:\*\*  

Validation passes with no error for the diagnosis code.



\*\*Status:\*\* Open  

\*\*Reported By:\*\* QA  



---



\### DEF-003 – Claim saved even when validation errors are present



\- \*\*Module:\*\* Save Operation  

\- \*\*Severity:\*\* Critical  

\- \*\*Priority:\*\* High  

\- \*\*Environment:\*\* QA  



\*\*Steps to Reproduce:\*\*

1\. Enter claim data with an invalid claim amount (e.g., 50,000).

2\. Click \*\*Validate\*\* and observe that an error message appears.

3\. Without correcting the error, click \*\*Save Claim\*\*.



\*\*Expected Result:\*\*  

System should prevent saving the claim until all validation errors are resolved.



\*\*Actual Result:\*\*  

System allows saving the claim and generates a Claim ID.



\*\*Status:\*\* Open  

\*\*Reported By:\*\* QA  



---



\### DEF-004 – Incorrect error message text for missing Date of Birth



\- \*\*Module:\*\* Claim Creation  

\- \*\*Severity:\*\* Medium  

\- \*\*Priority:\*\* Medium  

\- \*\*Environment:\*\* QA  



\*\*Steps to Reproduce:\*\*

1\. Leave \*\*Date of Birth\*\* field blank.

2\. Fill all other mandatory fields with valid data.

3\. Click \*\*Validate\*\*.



\*\*Expected Result:\*\*  

Error message: “Date of Birth is required”.



\*\*Actual Result:\*\*  

Error message displayed: “DOB missing. Please fix your info.” (Informal and inconsistent with other error messages.)



\*\*Status:\*\* Open  

\*\*Reported By:\*\* QA  



---



