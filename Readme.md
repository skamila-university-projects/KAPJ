## KAPJ

Repozytorium zawiera dwie gałęzie:
* zadania
* master 

Na gałęzi zadania znajdują się zadania przedstawione na zajęciach z KAPJ (ponumerowane w opisach commitów).

Na gałęzi master znajduje się projekt - aplikacja webowa służąca do rejestracji wizyt medycznych. 


#### Aplikacja umożliwa:
* logowanie i rejestracja (Spring Security)
* założenie konta pacjenta (walidacja pól, zabezpieczenie mechanizmem reCaptcha, potwierdzenie rejestracji wysyłane na maila)
* założenie kont przez administratora (konta administratora, lekarza, pacjenta)
* rejestracja wizyt medycznych przez pacjentów
* anulowanie wizyt przez pacjenta
* potwierdzanie i anulowanie wizyt przez lekarza oraz administratora
* automatyczne anulowanie wizyt, które nie są potwierdzone (w celach prezentacyjnych, wizyty starsze niż 2 min są odrzucane)
* internacjonalizacja (język polski, angielski i islandzki)
* pobieranie rachunków potwierdzonych wizyt (w formacie .pdf) 
* kontroler rest service (pobierenie wizyty i użytkownika w json oraz xml)


#### Wymagania:
* Java 8
* Maven
* PostgreSQL


#### Uruchomienie:

Aby funkcjonalność wysyłania linków aktywacyjnych działała, należy w klasie SpringConfiguration wypełnić 
odpowiednimi danymi pola MAIL i PASSWORD.

Po uruchomieniu projektu i postawieniu bazy należy uruchomić poniższe polecenia w celu utworzenia potrzebnych ról oraz dodania pierwszego konta administratora.

```
insert into app_user_role (id, role) values (nextval ('app_user_role_id_seq'), 'ROLE_ADMIN');
insert into app_user_role (id, role) values (nextval ('app_user_role_id_seq'), 'ROLE_DOCTOR');
insert into app_user_role (id, role) values (nextval ('app_user_role_id_seq'), 'ROLE_PATIENT');

insert into app_user (id, email, enabled, firstname, lastname, login, password) 
values (nextval ('app_user_id_seq'), 'admin@poczta.pl', true, 'Admin', 'Admin', 'admin', '$2a$10$EMwCm1h5fP/W8.CMINsw8.g5oxva1z558a6bP9nLomYcUn.Xgb7D2');

insert into app_user_app_user_role (appuser_id, appuserrole_id) values (1, 1);
```
