# Sistema de Locação de Veículos

Este projeto é um sistema de locações desenvolvido em Java com JavaFX. Ele utiliza uma arquitetura modular baseada em Maven, permitindo o carregamento dinâmico de plugins sem alterar o núcleo da aplicação.

## Pré-requisitos

Para compilar e executar este projeto via linha de comando (sem necessidade de IDE), você precisa ter instalado no seu sistema:

* **Java JDK 17** ou superior.
* **Apache Maven** (instalado e configurado no `PATH`).
* **MariaDB** ou **MySQL** (rodando na porta 3306).

---

## Configuração do Banco de Dados

Antes de executar, certifique-se de que o banco de dados está acessível conforme as configurações *hardcoded* nos plugins:

* **URL:** `jdbc:mariadb://localhost:3306/inf008`
* **Usuário:** `root`
* **Senha:** `root`

Certifique-se de que as tabelas `vehicles`, `rentals`, `customers` e `vehicle_types` estejam criadas e populadas.

---

## Instruções de Compilação (Build)

Este projeto utiliza o Maven para gerenciamento de dependências e construção. Para compilar todos os módulos (Core, Interfaces e Plugins), abra o terminal na **raiz do projeto** e execute:

```bash
mvn clean install
```

# Execution instructions:

mvn exec:java -pl app

# New plugin creation instructions:

1. Create your plugin folder in "plugins"
2. Add you new plugin submodule in main pom.xml:

    `<modules>`
        `<module>interfaces</module>`
        `<module>app</module>`
        `<module>plugins/myplugin</module>`
        ADD IT HERE
    `</modules>`
    
3. Create your new plugin's pom.xml (check myplugin/pom.xml)
4. Remember to use plugin's package conventions:

    br/edu/ifba/inf008/app/plugins/<YourPluginNameInCamelCase>.java
    
5. Run "mvn install" and "mvn exec:java -pl app"
## Link Youtube Video
https://youtu.be/ejEH3gYfOhQ