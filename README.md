# \# Medical Claims Validation – Healthcare QA Automation Project

# 

This project demonstrates a complete \*\*end-to-end QA case study\*\* on a healthcare domain system focused on \*\*Medical Claims Validation\*\*, where insurance claim submissions must follow regulatory standards such as mandatory patient information, proper diagnosis/procedure code formatting, and financial checks.



The work covers the \*\*full SDLC + STLC\*\* cycle:

✓ Requirement Analysis  

✓ Test Planning \& Strategy  

✓ Test Scenarios and Traceability  

✓ Functional Testing \& Defect Logging  

✓ Test Execution Report  

✓ Automation Framework Development  



---



\## 🎯 Project Goal



To validate that the Medical Claims portal correctly:

\- Enforces mandatory fields

\- Performs ICD/Procedure code format validations

\- Rejects invalid or missing claim amounts

\- Prevents saving a claim with pending validation errors



This ensures patient claim accuracy and compliance in healthcare processing.



---



\## 📁 Project Structure



| Folder | Description |

|--------|-------------|

| `docs/` | All Manual QA Deliverables (SRS, RTM, Strategy, Test Scenarios, Defect Log, Reports) |

| `automation/selenium-java/` | Selenium + TestNG automation framework |

| `automation/selenium-java/src/test/java/com/medsafety/tests/` | Automated test scripts |



---



\## 🧪 Manual QA Deliverables (Phase-1)



| Artifact | Status | Purpose |

|---------|--------|---------|

| SRS – Requirements | Complete | Scope definition \& validation rules |

| Test Strategy Plan | Complete | Approach, Exit criteria \& Test levels |

| Test Scenarios \& Cases | Complete | Functional coverage |

| RTM | Complete | Requirement-to-test traceability |

| Defect Log | Complete | Defects from execution (Critical/High issues logged) |

| Test Execution Report | Complete | Summary of pass/fail \& readiness indicators |



These assets simulate a real healthcare IT QA environment.



---



\## 🤖 Automation – Phase-1 Integration



Although development UI is still under construction, automation has been integrated by testing \*\*real UI components on a live application\*\* to demonstrate SDET skills.



Platform automated:  

\*\*Swag Labs E-commerce Demo Web App\*\*  

URL: `https://www.saucedemo.com/`



Reason:

\- Production-grade UI with real workflows

\- Demonstrates form validation, navigation, cart, checkout

\- Showcases test automation without waiting for product UI completion



\### Automated Test Scenarios (Selenium + Java + TestNG)



| Test Case | Status | Tools Used |

|----------|--------|------------|

| Valid User Login | ✔ Passed | Selenium + TestNG |

| Invalid Credentials Login | ✔ Passed | Selenium + TestNG |

| Add Item to Cart \& Checkout Flow | ⚠ Present in code, popup under review | Selenium + TestNG |



Later execution will be pointed toward healthcare UI once dev build is ready.



---



\## 🛠 Tech Stack



| Category | Tools |

|---------|------|

| Language | Java |

| Test Framework | TestNG |

| Automation | Selenium WebDriver, WebDriverManager |

| Build Tool | Maven |

| Version Control | Git \& GitHub |

| Domain Focus | Healthcare – Claims \& Compliance |



---



\## ▶️ How to Execute the Automation



Inside repository:



```bash

cd automation/selenium-java

mvn test



