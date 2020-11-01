package spiral.bit.dev.movcinema.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Popular implements Parcelable {

    @SerializedName("results")
    @Expose
    private final List<PopularResult> results = new ArrayList<>();
    public final static Parcelable.Creator<Popular> CREATOR = new Creator<Popular>() {


        public Popular createFromParcel(Parcel in) {
            return new Popular(in);
        }

        public Popular[] newArray(int size) {
            return (new Popular[size]);
        }
    };

    protected Popular(Parcel in) {
        in.readList(this.results, (spiral.bit.dev.movcinema.models.PopularResult.class.getClassLoader()));
    }

    public List<PopularResult> getResults() {
        return results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
