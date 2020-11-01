package spiral.bit.dev.movcinema.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Top implements Parcelable {

    @SerializedName("results")
    @Expose
    private final List<TopResult> results = new ArrayList<>();
    public final static Parcelable.Creator<Top> CREATOR = new Creator<Top>() {

        public Top createFromParcel(Parcel in) {
            return new Top(in);
        }

        public Top[] newArray(int size) {
            return (new Top[size]);
        }
    };

    protected Top(Parcel in) {
        in.readList(this.results, (spiral.bit.dev.movcinema.models.TopResult.class.getClassLoader()));
    }

    public List<TopResult> getResults() {
        return results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }
}
