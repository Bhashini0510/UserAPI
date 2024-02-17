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
Feature: Positive Scenario of our User API feature
   
Scenario Outline: POST GET PUT DELETE API Validation
	Given Add User Details Payload with "<firstname>" "<lastname>" <contact> "<mail>" "<country>" <zipCode> "<state>" "<plot>" "<street>"
	When user calls "PostAPI" with "POST" http request
	Then the API call got success with status code 201
	
	When user calls "GetBYIDAPI" with created userid
	Then the APIcall got success with status code 200	
	
  When user calls GetByNameAPI with created username
	Then the APIcall got success with status code 200
	
	When user calls GetByUsers with created username
	Then the APIcall got success with status code 200
	
	When user calls PutAPI to update
	Then the APIcall got success with status code 200
	
	When user calls DeleteByID
	Then the APIcall got success with status code 200
	
	Given Add User Details Payload with "<firstname>" "<lastname>" <contact> "<mail>" "<country>" <zipCode> "<state>" "<plot>" "<street>"
	When user calls "PostAPI" with "POST" http request
	
	When user calls DeleteByNAME
	Then the APIcall got success with status code 200
	
	
		Examples:
	|firstname 	 | lastname |contact		| mail              |country  | zipCode| state    | plot|street|
	|exploreer   |  duble   |5134867835 |rassar33@gmail.com |UK       | 41091  | Kentucky | 7A-B|Flornce|
	
	



