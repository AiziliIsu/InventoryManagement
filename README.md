Inventory Management System using Spring Boot. The system will integrate with a MPostgreSQL database and expose REST APIs for managing products, categories, and inventory stock levels. The project will include Swagger for API documentation.


1. Endpoints for Product, Inventroy and Category
   Create a Category
   ![Screenshot 2024-12-17 190251](https://github.com/user-attachments/assets/f0f2c698-69c8-44c5-bbf6-6e45f4f96158)
   ![Screenshot 2024-12-17 190354](https://github.com/user-attachments/assets/8a61342a-3245-4769-9d9c-abe9d39480b8)
   Create a Product
  ![Screenshot 2024-12-17 194044](https://github.com/user-attachments/assets/939d1d99-d002-4c5d-bf11-7b8f33d1b514)
  ![Screenshot 2024-12-17 194122](https://github.com/user-attachments/assets/da41a9d6-7be1-472c-b21c-33a7d8b3e7ed)
  Get All Products
  ![Screenshot 2024-12-17 194236](https://github.com/user-attachments/assets/4be5f0e5-d245-403b-a78e-90524a9dbb4a)
  Get a Product by ID
  ![Screenshot 2024-12-17 194236](https://github.com/user-attachments/assets/a86b6e30-e845-4ad3-9287-6ad8926cbea6)
  Update a Product
  ![Screenshot 2024-12-17 194335](https://github.com/user-attachments/assets/ef3ae754-030d-4b03-a690-315d0095780d)
  ![image](https://github.com/user-attachments/assets/478cf580-aae3-497c-9cb2-e882fe057519)
  Create Inventory
  ![Screenshot 2024-12-17 194427](https://github.com/user-attachments/assets/6b7763d4-4cf2-4d0c-8957-81f848288651)
  ![image](https://github.com/user-attachments/assets/aa7181d5-29db-4624-8acc-1f24a470d975)
  Update Inventory
  ![image](https://github.com/user-attachments/assets/90f4a64b-4cc7-4eb6-8cb8-2d6184339a45)
  ![image](https://github.com/user-attachments/assets/906597f2-30ff-4c0d-8723-0850f63dd59b)
  Get Inventory for a Product
  ![image](https://github.com/user-attachments/assets/6e01c8bf-ef5d-4a64-85d3-1882a5b21b88)
  Deduct Stock
  Deduct 10 from the stock of the specified product.
  If the stock is enough, it will deduct and return a 200 OK response.
  If there's not enough stock, it will return 400 Bad Request with an error message.




