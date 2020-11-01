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
    private final List<NowPlayingResult> results = new ArrayList<>();
    public final static Parcelable.Creator<NowPlaying> CREATOR = new Creator<NowPlaying>() {

        public NowPlaying createFromParcel(Parcel in) {
            return new NowPlaying(in);
        }

        public NowPlaying[] newArray(int size) {
            return (new NowPlaying[size]);
        }
    };

    protected NowPlaying(Parcel in) {
        in.readList(this.results, (spiral.bit.dev.movcinema.models.NowPlayingResult.class.getClassLoader()));
    }

    public List<NowPlayingResult> getResults() {
        return results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }
}
