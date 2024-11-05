```mermaid
erDiagram
    users {
        Long id PK
        varchar user_name
        varchar password
        varchar email
        varchar role
    }

    customers {
        Long id PK
        varchar customer_name
        varchar phone
        varchar address
    }

    suppliers {
        Long id PK
        varchar supplier_name
        varchar supplier_register
        varchar phone
        varchar address
    }

    supplier_orders {
        Long id PK
        Long supplier_id FK
        date supplier_order_date
        Double total
    }

    supplier_order_products {
        Long id PK
        Long supplier_order_id FK
        Long product_id FK
        double amount
    }

    products {
        Long id PK
        varchar product_name
        Long category_id FK
        double cost_price
        double selling_price
        integer amount
    }

    customer_order_products {
        Long id PK
        Long order_id FK
        Long product_id FK
        double amount
    }

    customer_orders {
        Long id PK
        Long customer_id FK
        date customer_order_date
        Double total
    }

    categories {
        Long id PK
        varchar category_name
    }

    %% Relationships
    categories ||--o{ products : "has many"
    customers ||--o{ customer_orders : "places"
    customer_orders ||--o{ customer_order_products : "contains"
    products ||--o{ customer_order_products : "ordered in"
    suppliers ||--o{ supplier_orders : "places"
    supplier_orders ||--o{ supplier_order_products : "contains"
    products ||--o{ supplier_order_products : "supplied in"
