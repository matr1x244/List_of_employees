# List_of_employees
![Screenshot_1650998303](https://user-images.githubusercontent.com/68413503/165369333-a2957be8-476f-4966-ba20-cca693a21e4c.png)

Минимальная поддерживаемая версия Android API - 19;

Приложение cостоит из одного экрана со списком (этот экран показывается сразу при открытии приложения);

Для отображения списка используется элемент RecyclerView;

Список данных в формате JSON приложение загружает из интернета по ссылке http://www.mocky.io/v2/5ddcd3673400005800eae483

Список отсортирован по алфавиту с учётом name Employee, с удалением после сортировки, где name == null и phone == null.
