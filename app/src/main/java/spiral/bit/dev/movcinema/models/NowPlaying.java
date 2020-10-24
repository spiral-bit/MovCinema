package spiral.bit.dev.movcinema.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class NowPlaying implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<NowPlayingResult> results = new ArrayList<>();
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Parcelable.Creator<NowPlaying> CREATOR = new Creator<NowPlaying>() {

        @SuppressWarnings({
                "unchecked"
        })
        public NowPlaying createFromParcel(Parcel in) {
            return new NowPlaying(in);
        }

        public NowPlaying[] newArray(int size) {
            return (new NowPlaying[size]);
        }
    };

    protected NowPlaying(Parcel in) {
        in.readList(this.results, (spiral.bit.dev.movcinema.models.NowPlayingResult.class.getClassLoader()));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dates = ((Dates) in.readValue((Dates.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public List<NowPlayingResult> getResults() {
        return results;
    }

    public void setResults(List<NowPlayingResult> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}
