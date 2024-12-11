public class TimeTrackerApp {
    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // Beispiel: Neues Thema hinzufügen
        dbHandler.addTheme("Project Work");


        // Beispiel: Neuen Zeiteintrag hinzufügen
        dbHandler.addTimeEntry("2024-12-11", "09:00", "17:00", "Development", 8.0, "Project Work");

        // Beispiel: Alle Einträge anzeigen
        dbHandler.showAllEntries();

        // Beispiel: Zeiteintrag entfernen
        dbHandler.removeTimeEntry(1);

        // Beispiel: Alle Einträge anzeigen
        dbHandler.showAllEntries();

        // Verbindung schließen
        dbHandler.close();
    }
}
