# PF System API Documentation

This documentation provides an overview of the API endpoints, their descriptions, request and response formats, and the data structures used in the API.

Table of contents:

1. [Introduction](#introduction)
2. [Base URL](#base-url)
3. [API](#api)
4. [Schemas](#schemas)
5. [Conclusion](#conclusion)

## Introduction

This API provides functionality for managing SIM card orders and recharges. It includes operations such as processing recharges, ordering new sim card, alerting customers with Email, SMS and tracking order status. The API follows the OpenAPI 3.0.1 specification.

## Base URL

- Base URL: [http://localhost:8080](http://localhost:8080)

## API

****Order Sim Controller****

### 1. Get Network Operators

- **Method:** GET
- **Endpoint:** `/ordersim/getOperator`
- **Description:** Get a list of available network operators.
- **Schemas:** NetworkOperatorDto
- **Example Request:**

  ```http
  GET /ordersim/getOperator
  ```

- **Example Response:**

  ```json
  [
    {
      "id": 93,
      "operatorCircle": "Andhra Pradesh",
      "operator": "Jio"
    },
    {
      "id": 18,
      "operatorCircle": "Chennai",
      "operator": "Airtel"
    }
  ]
  ```

### 2. Generate Order ID

- **Method:** GET
- **Endpoint:** `/ordersim/createOrderID`
- **Description:** Generate a unique order ID for new SIM card order.
- **Schemas:** OrderIDDto

- **Example Request:**

  ```http
  GET /ordersim/createOrderID
  ```

- **Example Response:**

  ```json
  {
    "orderID": "ORD67890"
  }
  ```

### 3. Order a New SIM Card

- **Method:** POST
- **Endpoint:** `/ordersim/newSim/{id}`
- **Description:** Place an order for a new SIM card.
- **Schemas:** NewSimDto
- **Example Request:**

  ```http
  POST /ordersim/newSim/18
  Content-Type: application/json

  {
      "firstName": "Raju",
      "lastName": "Singh",
      "email": "raju.s@gmail.com",
      "existingNumber": "9876543210",
      "addressLine1": "Flat no:10 123 Main Street",
      "addressLine2": "Apt 45",
      "city": "Hyderabad",
      "state": "Telangana",
      "pincode": "500002",
      "aadhaarCard": "123456789012",
      "type": "Prepaid",
      "orderID": "ORD67890"
  }
  ```

- **Example Response:**

  ```json
  {
    "responseBody": "Sim card created successfully"
  }
  ```

### 4. Get SIM Card Order Details For Billing

- **Method:** GET
- **Endpoint:** `/ordersim/getOrderDetails`
- **Description:** Get details of a new SIM card order and send notification of order via SMS and Email.
- **Schemas:** NewSimOrderStatusDto
- **Example Request:**

  ```http
  GET /ordersim/getOrderDetails?orderID=ORD12345
  ```

- **Example Response:**

  ```json
  {
    "name": "Raju Singh",
    "email": "raju.s@gmail.com",
    "address": "Flat no:10 123 Main Street, Apt 45, Hyderabad, Telangana, 500002.",
    "existingNumber": "9876543210",
    "newSimNumber": "6784567890",
    "price": "49.99",
    "orderTime": "2023-10-29T15:30:00Z",
    "paidVia": "COD",
    "status": "Success"
  }
  ```

### 5. Get OTP for Tracking New SIM Card Status

- **Method:** GET
- **Endpoint:** `/ordersim/getOTPNewSim`
- **Description:** Get an OTP to track the new SIM card status.
- **Schemas:** MobileNumberDto
- **Example Request:**

  ```http
  GET /ordersim/getOTPNewSim?orderID=ORD12345
  ```

- **Example Response:**

  ```json
  {
    "mobileNumber": "9876543210"
  }
  ```

### 6. Validate OTP and send order status

- **Method:** POST
- **Endpoint:** `/ordersim/validateOTP`
- **Description:** Validate the OTP and fetch Order status details.
- **Schemas:**  NewSimOrderStatusDto or ResponseDto
- **Example Request:**

  ```http
  POST /ordersim/validateOTP?orderID=ORD67890&inputOtp=123456&mobileNumber=9876543210
  ```

- **Example Response:**

  ```json
  {
    "name": "Raju Singh",
    "email": "raju.s@gmail.com",
    "address": "Flat no:10 123 Main Street, Apt 45, Hyderabad, Telangana, 500002.",
    "existingNumber": "9876543210",
    "newSimNumber": "6784567890",
    "price": "49.99",
    "orderTime": "2023-10-29T15:30:00Z",
    "paidVia": "COD",
    "status": "Success"
  }
  or
  {
  "responseBody": "Invalid OTP. Please try again."
  }
  ```

****Recharge Sim Controller****

### 7. Fetch Recharge Plans

- **Method:** POST
- **Endpoint:** `/rechargesim/getPlans`
- **Description:** Fetch available recharge plans for a specific operator and category.
- **Schemas:** RechargePlansDto
- **Example Request:**

  ```http
  POST /rechargesim/getPlans
  Content-Type: application/json

  {
      "operator": "Jio",
      "categoryName": "Combo"
  }
  ```

- **Example Response:**

  ```json
  [
    {
      "planID": 1,
      "price": "19.99",
      "validity": "30 days",
      "details": "Unlimited calls and 2GB data",
      "categoryName": "Combo"
    },
    {
      "planID": 2,
      "price": "29.99",
      "validity": "45 days",
      "details": "Unlimited calls and 4GB data",
      "categoryName": "Combo"
    }
  ]
  ```

### 8. Create Recharge Order ID

- **Method:** GET
- **Endpoint:** `/rechargesim/createOrderID`
- **Description:** Generate a unique order ID for recharges.
- **Schemas:** OrderIDDto
- **Example Request:**

  ```http
  GET /rechargesim/createOrderID
  ```

- **Example Response:**

  ```json
  {
    "orderID": "RECHARGE123"
  }
  ```

### 9. Process Recharge

- **Method:** POST
- **Endpoint:** `/rechargesim/processRecharge`
- **Description:** Process a recharge for a mobile number.
- **Schemas:** ResponseDto
- **Example Request:**

  ```http
  POST /rechargesim/processRecharge
  Content-Type: application/json

  {
      "operator": "Jio",
      "mobileNumber": "9876543210",
      "operatorCircle": "Andhra Pradesh",
      "planID": 1,
      "payVia": "UPI ID",
      "paymentInfo": "raju@okhdfc",
      "orderID": "RECHARGE123"
  }
  ```

- **Example Response:**

  ```json
  {
    "responseBody": "Recharge successful"
  }
  ```

### 10. Get Recharge Order Details

- **Method:** GET
- **Endpoint:** `/rechargesim/getRechargeOrderDetails/{orderID}`
- **Description:** Get details of a recharge order using the order ID and send SMS.
- **Schemas:** RechargeStatusDto
- **Example Request:**

  ```http
  GET /rechargesim/getRechargeOrderDetails/RECHARGE123
  ```

- **Example Response:**

  ```json
  {
    "mobileNumber": "9876543210",
    "operator": "Jio",
    "operatorCirle": "Andhra Pradesh",
    "finalAmount": "19.99",
    "orderStatus": "Success",
    "paidVia": "UPI ID",
    "paymentInfo": "raju@okhdfc",
    "validity": "30 days",
    "details": "Unlimited calls and 2GB data",
    "orderTime": "2023-10-29T16:45:00Z"
  }
  ```

## Schemas

### RechargeInfoDto

```json
{
  "operator": "string",
  "mobileNumber": "string",
  "operatorCircle": "string",
  "planID": "integer (int64)",
  "payVia": "string",
  "paymentInfo": "string",
  "orderID": "string"
}
```

### ResponseDto

```json
{
  "responseBody": "string"
}
```

### FetchPlansDto

```json
{
  "operator": "string",
  "categoryName": "string"
}
```

### RechargePlansDto

```json
{
  "planID": "integer (int64)",
  "price": "string",
  "validity": "string",
  "details": "string",
  "categoryName": "string"
}
```

### NewSimDto

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "existingNumber": "string",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "pincode": "string",
  "aadhaarCard": "string",
  "type": "string",
  "orderID": "string"
}
```

### RechargeStatusDto

```json
{
  "mobileNumber": "string",
  "operator": "string",
  "operatorCircle": "string",
  "finalAmount": "string",
  "orderStatus": "string",
  "paidVia": "string",
  "paymentInfo": "string",
  "validity": "string",
  "details": "string",
  "orderTime": "string (date-time)"
}
```

### OrderIDDto

```json
{
  "orderID": "string"
}
```

### NewSimOrderStatusDto

```json
{
  "name": "string",
  "email": "string",
  "address": "string",
  "existingNumber": "string",
  "newSimNumber": "string",
  "price": "string",
  "orderTime": "string (date-time)",
  "paidVia": "string",
  "status": "string"
}
```

### NetworkOperatorDto

```json
{
  "id": "integer (int64)",
  "operatorCircle": "string",
  "operator": "string"
}
```

### MobileNumberDto

```json
{
  "mobileNumber": "string"
}
```

## Conclusion

This API provides various endpoints for managing SIM card orders and recharges. It allows you to place new SIM card orders, fetch order details, validate OTP, and process recharges. Use the provided examples as a reference to interact with the API endpoints.
