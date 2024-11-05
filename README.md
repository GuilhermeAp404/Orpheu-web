# Database Schema

Abaixo está o diagrama do banco de dados, representado em Mermaid:

```mermaid
erDiagram
    users {
      Long id PK
      varchar user_name "65"
      varchar password "255"
      varchar email "90"
      varchar role "10"
    }

    customers {
      Long id PK
      varchar customer_name "65"
      varchar phone "11" UNIQUE NOT NULL
      varchar address "255" NOT NULL
    }

    suppliers {
      Long id PK
      varchar supplier_name "65"
      varchar supplier_register "14" UNIQUE NOT NULL
      varchar phone "11" UNIQUE NOT NULL
      varchar address "255" NOT NULL
    }

    supplier_orders {
      Long id PK
      Long supplier_id FK
      date supplier_order_date
      Double total
    }

    supplier_order_products {
      Long id PK
      Long supplier_order_id FK NOT NULL
      Long product_id FK NOT NULL
      double amount NOT NULL
    }

    products {
      Long id PK
      varchar product_name "255" UNIQUE NOT NULL
      Long category_id FK NOT NULL
      double cost_price NOT NULL
      double selling_price NOT NULL
      integer amount DEFAULT "0"
    }

    customer_order_products {
      Long id PK
      Long order_id FK NOT NULL
      Long product_id FK NOT NULL
      double amount NOT NULL
    }

    customer_orders {
      Long id PK
      Long customer_id FK
      date customer_order_date
      Double total
    }

    categories {
      Long id PK
      varchar category_name "24"
    }

    %% Relationships
    categories ||--o{ products : "id > category_id"
    customers ||--o{ customer_orders : "id > customer_id"
    customer_orders ||--o{ customer_order_products : "id > order_id"
    products ||--o{ customer_order_products : "id > product_id"

    suppliers ||--o{ supplier_orders : "id > supplier_id"
    supplier_orders ||--o{ supplier_order_products : "id > supplier_order_id"
    products ||--o{ supplier_order_products : "id > product_id"
```
