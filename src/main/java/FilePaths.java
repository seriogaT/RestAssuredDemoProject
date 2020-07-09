public enum FilePaths {

    DATA("src/test/resources/mapAPI.json");

    private String path;

    FilePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}