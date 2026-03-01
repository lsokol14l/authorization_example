Подпроект, появившийся для решения подзадачи одного из проектов s21

Необходимо реалзиовать авторизацию пользователей

технологии: SpringSecurity

весь путь запроса на вход моей авторизации:

1. Frontend отправляет: Authorization: Basic base64(john:secret)
   ↓
2. Spring Security декодирует: username="john", password="secret"
   ↓
3. Spring вызывает: userDetailsService.loadUserByUsername("john")
   ↓
4. UserDetailsServiceImpl:
   - userRepo.findByLogin("john") ← Ищем в БД
   - UserEntity → User ← Маппим
   - new SecurityUser(user) ← Оборачиваем
     ↓
5. Spring Security:
   - String username = userDetails.getUsername() ← "john"
   - String password = userDetails.getPassword() ← "$2a$05$..."
   - passwordEncoder.matches("secret", "$2a$05$...") ← Проверяет
     ↓
6. Если пароль верный:
   - Создает Authentication
   - Сохраняет в SecurityContext
   - Пользователь авторизован!
