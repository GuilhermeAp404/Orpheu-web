![Static Badge](https://img.shields.io/badge/Deploy-none-red)
![Static Badge](https://img.shields.io/badge/Status-in_progress-orange)
# Orpheu-Web-API
## Tecnologias utilizadas
<p align="left">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,hibernate,spring&theme=dark" />
  </a>
</p>
## Diagrama de classes e relacionamentos

```mermaid
classDiagram
    class User {
        -Long id
        -String username
        -String password
        -String email
        -String role
    }

    class Customer {
        -Long id
        -String customerName
        -String phone
        -String address
    }

    class Supplier {
        -Long id
        -String supplierName
        -String supplierRegister
        -String phone
        -String address
    }

    class SupplierOrder {
        -Long id
        -Supplier supplierId
        -Date supplierOrderDate
        -Double total
    }

    class SupplierOrderProduct {
        -Long id
        -Supplier supplierOrderId
        -Product productId
        -Double amount
    }

    class Product {
        -Long id
        -String productName
        -Category categoryId
        -Double costPrice
        -Double sellingPrice
        -Integer amount
    }

    class CustomerOrderProduct {
        -Long id
        -CustomerOrder customerOrderId
        -Product productId
        -Double amount
    }

    class CustomerOrder {
        -Long id
        -Customer customerId
        -Date customerOrderDate
        -Double total
    }

    class Category {
        -Long id
        -String categoryName
    }

    %% Relationships
    Category "1" -- "*" Product 
    Customer "1" -- "*" CustomerOrder 
    CustomerOrder "1" -- "*" CustomerOrderProduct 
    Product "1" -- "*" CustomerOrderProduct 
    Supplier "1" -- "*" SupplierOrder 
    SupplierOrder "1" -- "*" SupplierOrderProduct 
    Product "1" -- "*" SupplierOrderProduct 
