public class TimeTrackerApp {
    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        /*

        // Beispiel: Neues Thema hinzufügen
        dbHandler.addTheme("Project Work");

        // Beispiel: Neuen Zeiteintrag hinzufügen
        dbHandler.addTimeEntry("2024-12-12", "09:00", "17:00", "Development", 8.0, "Project Work");

        // Beispiel: Alle Einträge anzeigen
        dbHandler.showAllEntries();

        // Beispiel: Zeiteintrag entfernen
        dbHandler.removeTimeEntry(1);

        // Beispiel: Alle Einträge anzeigen
        dbHandler.showAllEntries();

        // Beispiel: Ein Zeiteintrag bearbeiten
        dbHandler.updateTimeEntry(1, "2024-12-12", "08:00", "16:00", "Updated Project", 8.0, "Updated Theme");

         */

        // Verbindung schließen
        dbHandler.close();
    }
}
