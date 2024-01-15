import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.movies.MovieEntity
import com.bignerdranch.android.movies.R
import com.squareup.picasso.Picasso

class MovieDbAdapter(private var movies: List<MovieEntity>) :
    RecyclerView.Adapter<MovieDbAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val yearTextView: TextView = itemView.findViewById(R.id.textViewYear)
        val posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_db_movie, parent, false)
        return MovieViewHolder(itemView)
    }
    fun setData(newMovies: List<MovieEntity>) {
        movies = newMovies
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.titleTextView.text = currentMovie.title
        holder.yearTextView.text = currentMovie.year
        Picasso.get()
            .load(currentMovie.posterUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.posterImageView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
