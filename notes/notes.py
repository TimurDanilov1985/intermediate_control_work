import json
import datetime

def sampling_by_date(notes):
    date = input("Введите дату в формате dd/mm/yyyy для вывода заметок: ")
    a = 0
    for note in notes:
        date1 = list(note.get("Дата").split(", "))
        if date == date1[0]:
            print("Заметка номер", note.get("id"))
            print(note.get("Заголовок заметки"))
            print(note.get("Заметка"))
            print(note.get("Дата"))
            print()
            a = 1
            return notes
    if a == 0:
        print("Нет заметок по вашему запросу")

def deleting_note(notes):
     parameter = input("Введите номер заметки для удаления: ")
     a = 0
     for i in range(0, len(notes)):
        if str(notes[i].get("id")) == parameter:
            notes.pop(i)
            print(f"Заметка под номером {i + 1} удалена")
            a = 1
     if a == 0:
        print("Нет заметок по вашему запросу")
     return notes

def note_editing(notes):
    parameter = input("Введите номер заметки для редактирования: ")
    a = 0
    for i in range(0, len(notes)):
        if str(notes[i].get("id")) == parameter:
            id = notes[i].get("id")
            title = input('Введите новый заголовок заметки: ')
            body = input('Введите новый текст заметки: ')
            date = datetime.datetime.now().strftime('%d/%m/%Y, %H:%M:%S')
            new_note = {"id":id, "Заголовок заметки":title, "Заметка":body, "Дата":date}
            notes[i] = new_note
            print(f"Заметка под номером {i} изменена")
            a = 1
    if a == 0:
        print("Нет заметок по вашему запросу")
    return notes
        

def print_all_notes(notes):
    try:
        for note in notes:
            print("Заметка номер", note.get("id"))
            print(note.get("Заголовок заметки"))
            print(note.get("Заметка"))
            print(note.get("Дата"))
            print()
    except TypeError:
        print("Нет заметок по вашему запросу")

def print_selected_note(notes):
    parameter = input("Введите номер заметки или заголовок заметки: ")
    for note in notes:
        a = 0
        if (str(note.get("id")) == parameter) or (note.get("Заголовок заметки") == parameter):
            print("Заметка номер", note.get("id"))
            print(note.get("Заголовок заметки"))
            print(note.get("Заметка"))
            print(note.get("Дата"))
            print()
            a = 1
    if a == 0:
        print("Нет заметок по вашему запросу")
        

def read_all_notes(notes ,file_name):
    with open (file_name, 'r') as f:
        try:
            notes = json.load(f)
            return notes
        except json.decoder.JSONDecodeError:
            print("У вас пока нет ни одной заметки\n")
            notes = []
            return notes

def writing_to_file(notes, file_name):
    with open (file_name, 'w') as f:
        json.dump(notes, f)
    
def add_note(notes):
    try:
        id = len(notes) + 1
    except TypeError:
        id = 1
        notes = []
    title = input('Введите заголовок заметки: ')
    body = input('Введите текст заметки: ')
    date = datetime.datetime.now().strftime('%d/%m/%Y, %H:%M:%S')
    new_note = {"id":id, "Заголовок заметки":title, "Заметка":body, "Дата":date}
    notes.append(new_note)
    return notes

def menu():
    print('Здравствуйте\nДобро пожаловать в приложение ЗАМЕТКИ\n')
    file_name = 'C:\\intermediate_control_work\\notes\\notes.json'
    notes = []
    notes = read_all_notes(notes, file_name)
    while True:
        print("Введите номер команды:\n1 - для добавления новой заметки\n2 - для вывода всех заметок на экран\n" + 
        "3 - для вывода выбранных заметок по номеру или заголовку\n4 - для вывода заметок по дате\n5 - для удаления выбранной заметки по номеру\n" + 
        "6 - для редактирования заметки\n7 - для выхода из программы")
        number = input("Введите команду: ")
        print()
        if number == "1":
            notes = add_note(notes)
            print("Введите 1 для сохранения заметки\nВведите 0 для выхода")
            num = input("Введите команду: ")
            if num == "1":
                writing_to_file(notes, file_name)
                print("Заметка сохранена")
                print()
            else:
                continue
        elif number == "2":
            print_all_notes(notes)
        elif number == "3":
            print_selected_note(notes)
        elif number == "4":
            sampling_by_date(notes)
        elif number == "5":
            notes = deleting_note(notes)
            print("Введите 1 для сохранения изменений\nВведите 0 для выхода")
            num = input("Введите команду: ")
            if num == "1":
                writing_to_file(notes, file_name)
                print("Заметка удалена")
                print()
            else:
               continue
        elif number == "6":
            notes = note_editing(notes)
            print("Введите 1 для сохранения новой заметки\nВведите 0 для выхода")
            num = input("Введите команду: ")
            if num == "1":
                writing_to_file(notes, file_name)
                print("Новая заметка сохранена")
                print()
            else:
                continue
        elif number == "7":
            break

if __name__ == '__main__':
    menu()