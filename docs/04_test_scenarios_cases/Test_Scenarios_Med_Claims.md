# Test Scenarios

This document contains detailed test scenarios for the Medical Claims Validation system.



\# Test Scenarios – Medical Claims Validation Portal (Phase 1)



\## 1. High-Level Test Scenarios



TS01 – Create claim with all valid data and verify it is marked as valid and can be saved.  

TS02 – Create claim with one or more mandatory fields missing and verify appropriate error messages.  

TS03 – Enter invalid diagnosis code format and verify validation error is shown.  

TS04 – Enter invalid procedure code format and verify validation error is shown.  

TS05 – Enter claim amount = 0 or negative and verify validation error is shown.  

TS06 – Enter claim amount greater than 10,000 and verify validation error is shown.  

TS07 – Enter multiple diagnosis and procedure codes (all valid) and verify claim can be validated and saved.  

TS08 – Trigger Validate without entering any data and verify all mandatory field errors are shown.  

TS09 – Correct errors after initial validation and verify that the claim can be successfully re-validated and saved.  

TS10 – Verify that error messages are retained and data is not lost when validation fails.



---



\## 2. Detailed Test Cases – Claim Amount (Equivalence Partitioning \& BVA)



\*\*Business Rule:\*\*  

\- Claim Amount must be > 0 and ≤ 10,000.



\### 2.1 Equivalence Partitions



\- EP1: Amount ≤ 0 → Invalid  

\- EP2: 0 < Amount ≤ 10,000 → Valid  

\- EP3: Amount > 10,000 → Invalid  



\### 2.2 Test Cases



| TC ID | Description                                      | Amount   | Expected Result                              | Technique                 |

|------|--------------------------------------------------|----------|----------------------------------------------|---------------------------|

| TC\_AMT\_01 | Enter Amount = 0                            | 0        | Validation error: amount must be > 0         | EP (Invalid – EP1)       |

| TC\_AMT\_02 | Enter Amount = -50                          | -50      | Validation error: amount must be > 0         | EP (Invalid – EP1)       |

| TC\_AMT\_03 | Enter Amount = 1                            | 1        | Amount accepted, no validation error         | BVA (Lower boundary)     |

| TC\_AMT\_04 | Enter Amount = 9,999                        | 9999     | Amount accepted, no validation error         | EP (Valid – EP2)         |

| TC\_AMT\_05 | Enter Amount = 10,000                       | 10000    | Amount accepted, no validation error         | BVA (Upper boundary)     |

| TC\_AMT\_06 | Enter Amount = 10,001                       | 10001    | Validation error: amount must be ≤ 10,000    | BVA (Just above boundary)|

| TC\_AMT\_07 | Enter Amount = 50,000                       | 50000    | Validation error: amount must be ≤ 10,000    | EP (Invalid – EP3)       |



---



\## 3. Detailed Test Cases – Mandatory Fields



\*\*Mandatory Fields (per SRS FR2):\*\*  

\- Patient Name  

\- Date of Birth  

\- Date of Service  

\- At least one Diagnosis Code  

\- At least one Procedure Code  

\- Claim Amount  



\### 3.1 Test Cases



| TC ID | Description                                              | Missing Field        | Expected Result                                       |

|-------|----------------------------------------------------------|----------------------|------------------------------------------------------|

| TC\_MAN\_01 | Leave Patient Name blank                             | Patient Name         | Error: “Patient Name is required”                    |

| TC\_MAN\_02 | Leave Date of Birth blank                            | Date of Birth        | Error: “Date of Birth is required”                   |

| TC\_MAN\_03 | Leave Date of Service blank                          | Date of Service      | Error: “Date of Service is required”                 |

| TC\_MAN\_04 | Do not enter any Diagnosis Code                      | Diagnosis Code       | Error: “At least one Diagnosis Code is required”     |

| TC\_MAN\_05 | Do not enter any Procedure Code                      | Procedure Code       | Error: “At least one Procedure Code is required”     |

| TC\_MAN\_06 | Do not enter Claim Amount                            | Claim Amount         | Error: “Claim Amount is required”                    |

| TC\_MAN\_07 | Leave multiple fields blank (e.g., Name + Amount)    | Multiple             | All relevant error messages displayed together       |



---



\## 4. Detailed Test Cases – Diagnosis Code Format



\*\*Rule (simplified for Phase 1):\*\*  

\- Starts with an alphabetic character  

\- Followed by 2–6 alphanumeric characters  

\- Optional dot after 3rd character (e.g., `A12.34`, `M16.0`)



\### 4.1 Test Cases



| TC ID | Code Input  | Description                                          | Expected Result                                      |

|-------|------------|------------------------------------------------------|-----------------------------------------------------|

| TC\_DX\_01 | `A12.34`  | Valid ICD-style code with dot after 3 characters     | Accepted, no validation error                       |

| TC\_DX\_02 | `M160`    | Valid ICD-style code without dot                     | Accepted, no validation error                       |

| TC\_DX\_03 | `112.34`  | Starts with digit instead of letter                  | Error: “Invalid diagnosis code format for 112.34”   |

| TC\_DX\_04 | `AB`      | Less than required minimum length                    | Error: “Invalid diagnosis code format for AB”       |

| TC\_DX\_05 | `A12.3#`  | Contains invalid special character `#`              | Error: “Invalid diagnosis code format for A12.3#”   |

| TC\_DX\_06 | `.A1234`  | Starts with dot                                     | Error: “Invalid diagnosis code format for .A1234”   |



---



\## 5. Decision Table – Validation Result Based on Conditions



\*\*Conditions:\*\*



\- C1: Mandatory fields complete? (Y/N)  

\- C2: All diagnosis codes valid format? (Y/N)  

\- C3: All procedure codes valid format? (Y/N)  

\- C4: Claim amount within valid range? (Y/N)  



\*\*Actions:\*\*



\- A1: Claim can be marked as valid  

\- A2: Claim cannot be marked as valid; show error messages  



| Rule | C1 (Mandatory OK) | C2 (Dx Valid) | C3 (Px Valid) | C4 (Amount Valid) | Outcome                     |

|------|-------------------|--------------|--------------|-------------------|-----------------------------|

| R1   | Y                 | Y            | Y            | Y                 | A1 – Claim Valid            |

| R2   | N                 | Y            | Y            | Y                 | A2 – Error: Missing Fields  |

| R3   | Y                 | N            | Y            | Y                 | A2 – Error: Dx Format       |

| R4   | Y                 | Y            | N            | Y                 | A2 – Error: Px Format       |

| R5   | Y                 | Y            | Y            | N                 | A2 – Error: Amount Invalid  |

| R6   | N                 | N            | Y            | Y                 | A2 – Missing + Dx Errors    |

| R7   | N                 | Y            | N            | N                 | A2 – Multiple Errors        |

| R8   | N                 | N            | N            | N                 | A2 – All Conditions Failing |



These rules can be mapped to specific test cases to ensure full coverage.



---











