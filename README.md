# Software Requirements Specification (SRS) - Pseudo Fulfillment System

# Table of Contents
1. [Introduction](#introduction)
   - 1.1 [Purpose](#purpose)
   - 1.2 [Scope](#scope)
   - 1.3 [Technologies Used](#technologies-used)
2. [System Overview](#system-overview)
   - 2.1 [System Architecture](#system-architecture)
   - 2.2 [User Roles](#user-roles)
   - 2.3 [Use Cases](#use-cases)
3. [Functional Requirements](#functional-requirements)
   - 3.1 [User Interface](#user-interface)
   - 3.2 [Plan Selection](#plan-selection)
   - 3.3 [Phone Number Validation](#phone-number-validation)
   - 3.4 [SIM Card Activation](#sim-card-activation)
   - 3.5 [Billing Information](#billing-information)
   - 3.6 [Payment Processing](#payment-processing)
   - 3.7 [Confirmation](#confirmation)
   - 3.8 [Plan Activation](#plan-activation)
   - 3.9 [Error Handling](#error-handling)
4. [Non-Functional Requirements](#non-functional-requirements)
   - 4.1 [Performance](#performance)
   - 4.2 [Security](#security)
   - 4.3 [Usability](#usability)
   - 4.4 [Availability](#availability)
   - 4.5 [Scalability](#scalability)
5. [Testing](#testing)
   - 5.1 [Test Strategy](#test-strategy)
   - 5.2 [Test Cases](#test-cases)
6. [References](#references)
7. [Appendix](#appendix)

## 1. Introduction

### 1.1. Purpose

The purpose of this Software Requirements Specification (SRS) document is to define the requirements for the development of the Pseudo Fulfillment System, a mobile plan change order handling system for a Telco company. The system aims to streamline the process of activating new mobile plans for customers.

### 1.2. Scope

The scope of this project includes the design and development of both the frontend and backend components. It covers the creation of a user-friendly interface, plan selection, phone number validation, SIM card activation, billing information collection, payment processing, confirmation, plan activation, and error handling.

### 1.3. Technologies Used

The technologies used for this project are as follows:

- Frontend: Angular
- Backend: Spring Boot
- Database: MySQL

## 2. System Overview

### 2.1. System Architecture

The Pseudo Fulfillment System follows a client-server architecture, with Angular serving as the frontend client and Spring Boot as the backend server. The system communicates with a MySQL database for data storage and retrieval.

### 2.2. User Roles

The system involves the following user roles:

- Telco Customer: End-users who want to activate a new mobile plan.

### 2.3. Use Cases

The main use cases of the system include:

- User selects a mobile plan.
- User enters and validates their phone number.
- User requests a new SIM card if needed.
- User provides billing information and selects a payment method.
- System processes the payment and confirms the transaction.
- User receives a confirmation email or SMS.
- User's selected mobile plan is activated immediately upon successful payment.
- System handles errors gracefully.

## 3. Functional Requirements

### 3.1. User Interface

The user interface should be designed to be intuitive and user-friendly, allowing customers to input the necessary information easily.

### 3.2. Plan Selection

Customers should be able to choose from a list of available mobile plans, including details such as plan name, data limits, talk time, and pricing.

### 3.3. Phone Number Validation

The system should validate customer phone numbers to ensure they are in the correct format (e.g., XXX-XXX-XXXX) and not already associated with another active plan.

### 3.4. SIM Card Activation

Customers should have the option to request a new SIM card if needed or enter the SIM card number if they already have a compatible one.

### 3.5. Billing Information

Customers must provide billing information, including their name, address, and payment method (e.g., credit card, PayPal).

### 3.6. Payment Processing

The system should process payments based on the selected plan and payment method. It should provide a clear confirmation of the payment status (success or failure).

### 3.7. Confirmation

Upon successful payment, the customer should receive a confirmation email or SMS with details of the activated plan, billing information, and next steps.

### 3.8. Plan Activation

The selected mobile plan should be activated immediately upon successful payment, allowing the customer to make calls, send texts, and use data with the new plan.

### 3.9. Error Handling

The system should handle errors gracefully, including failed payments, network issues, or incorrect input data, and provide clear error messages to the customer.

## 4. Non-Functional Requirements

### 4.1. Performance

The system must perform efficiently, with quick response times, even during peak usage.

### 4.2. Security

Security measures should be in place to protect user data and payment information.

### 4.3. Usability

The system should be designed for ease of use, with an intuitive interface.

### 4.4. Availability

The system should be highly available, with minimal downtime for maintenance.

### 4.5. Scalability

The system should be scalable to accommodate a growing number of users and mobile plans.

## 5. Testing

### 5.1. Test Strategy

A comprehensive testing strategy will be employed, including unit tests, integration tests, and user acceptance testing, to ensure the application functions correctly.

### 5.2. Test Cases

Detailed test cases will be developed to cover various scenarios, including plan selection, payment processing, error handling, and more.

## 6. References

N/A

## 7. Appendix

N/A
