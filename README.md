Подпроект, появившийся для решения подзадачи одного из проектов s21

Необходимо реалзиовать авторизацию пользователей

технологии: SpringSecurity

весь путь запроса на вход моей авторизации:

1. Frontend отправляет: Authorization: Basic base64(john:secret)

   Пример данных после 1 этапа: const token = btoa(username.value + ':' + password.value)
   ↓

2. Spring Security декодирует: username="john", password="secret"

   Spring, при помощи поведенческого паттерна проектирования шаблонный метод, забирает на себя контроль над boilerplate кодом процесса login'а, вызывая наши переопределенные методы

   ↓

3. Spring вызывает: userDetailsService.loadUserByUsername("john")

   Тут Spring вызывает наш Bean, в котором описана логика как и где искать пользоваетля по его username (такой контракт у спринга),
   соотвественно благодаря этому мы зависим только от контракта поиска, а реализовать мы можем его как нам угодно и на какой угодно технологии(в любой БД, хоть в коде данные хранить)

   ↓

4. UserDetailsServiceImpl:
   - userRepo.findByLogin("john") ← Ищем в БД
   - UserEntity → User ← Маппим
   - new SecurityUser(user) ← Оборачиваем

     Тут уже наша реализация контракта, в которой мы явно говрим, мол ищи при помощи нашего репозитория по Login (уже наш логин из бинзнес логики а не общий username), заетм нужно вернуть объект класса обертки SecurityUser, внутри которого будет лежать наша полученная сущность, здесь Spring применяет структурный паттерн проектирования - Адаптер для адаптации нашего класса нашей бизнес задачи к общему подходу Spring'а по login'у пользователей

     ↓

5. Spring Security:
   - String username = userDetails.getUsername() ← "john"
   - String password = userDetails.getPassword() ← "$2a$05$..."
   - passwordEncoder.matches("secret", "$2a$05$...") ← Проверяет

     Подкопотная логика процесса login'а

     ↓

6. Если пароль верный:
   - Создает Authentication
   - Сохраняет в SecurityContext
   - Пользователь авторизован!
