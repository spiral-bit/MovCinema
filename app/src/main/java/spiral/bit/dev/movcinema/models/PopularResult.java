package spiral.bit.dev.movcinema.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularResult implements Parcelable {

    @SerializedName("popularity")
    @Expose
    private final Double popularity;
    @SerializedName("poster_path")
    @Expose
    private final String posterPath;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("adult")
    @Expose
    private final Boolean adult;
    @SerializedName("original_language")
    @Expose
    private final String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private final String originalTitle;
    @SerializedName("overview")
    @Expose
    private final String overview;
    @SerializedName("release_date")
    @Expose
    private final String releaseDate;
    public final static Parcelable.Creator<PopularResult> CREATOR = new Creator<PopularResult>() {

        public PopularResult createFromParcel(Parcel in) {
            return new PopularResult(in);
        }

        public PopularResult[] newArray(int size) {
            return (new PopularResult[size]);
        }

    };

    protected PopularResult(Parcel in) {
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(popularity);
        dest.writeValue(posterPath);
        dest.writeValue(id);
        dest.writeValue(adult);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeValue(overview);
        dest.writeValue(releaseDate);
    }

    public int describeContents() {
        return 0;
    }
}
