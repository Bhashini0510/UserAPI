#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Post Negative Feature


  Scenario Outline: POST Negative Validation
  Given Add User Details Payload with "<Scenario>"
  When user calls POST API request
	Then the API call got status code

   
   Examples: 
   |Scenario|
   |Firstname Empty|
   |Last name Empty|
   |email id Empty|
   |Firstname as Number|
   |First name with special character|
   |Last name as Number|
   |Last Name with special character|
   |Contact Number less than 10 digits|
   |Contact Number more than 10 digits|
   |User Email Incorrect format|
   |Plot number only numbers|
   |Plot number only numbers and alphabets|
   |Plot number special characters other than &-|
   |Plot number only empty|
   |State is empty|
   |State numbers|
   |State alpha numbers|
   |Country alpha numbers|
   |Country is empty|
   |Street is empty|
   |Country numbers|
   |Contact number is already in use|
   |Email id already exists|
   
   