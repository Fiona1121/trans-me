@Document("audiofile")
public class AudioFile {

        @Id
        private String id;

        private String name;
        private String format;
        
        public AudioFile(String id, String name, String format) {
            super();
            this.id = id;
            this.name = name;
            this.format = format;
        }
}
