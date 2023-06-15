DROP TABLE IF EXISTS workspace;

CREATE TABLE workspace (
    service_key             varchar(100)    NOT NULL ,
    name                    varchar(100)    NOT NULL ,
    quartz_connection       varchar(1000)   NULL,
    batch_connection        varchar(1000)   NULL,
    created_at              datetime        NOT NULL ,
    updated_at              datetime        NULL ,

    PRIMARY KEY (service_key)
);

INSERT INTO workspace (service_key, name, quartz_connection, batch_connection, created_at)
VALUES (
           'core',
           '코어',
           '{
              "url":"jdbc:mysql://localhost:13306/core_db?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true",
              "username":"root",
              "password":"passwd",
              "driverClassName":"com.mysql.cj.jdbc.Driver",
              "tablePrefix":"QRTZ_"
           }',
           '{
              "url":"jdbc:mysql://localhost:13306/core_db?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true",
              "username":"root",
              "password":"passwd",
              "driverClassName":"com.mysql.cj.jdbc.Driver",
              "tablePrefix":"BATCH_"
           }',
           NOW()
       ),
        (
        'workspace-a',
        '워크스페이스 A',
        '{
           "url":"jdbc:mysql://localhost:13307/workspace_a_db?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true",
           "username":"root",
           "password":"passwd",
           "driverClassName":"com.mysql.cj.jdbc.Driver",
           "tablePrefix":"QRTZ_A_"
        }',
        '{
           "url":"jdbc:mysql://localhost:13307/workspace_a_db?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true",
           "username":"root",
           "password":"passwd",
           "driverClassName":"com.mysql.cj.jdbc.Driver",
           "tablePrefix":"BATCH_A_"
        }',
        NOW()
       );