package com.haredultra.greenbookhaven.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.settings.SettingsActivity

class ProfileFragment : Fragment() {

    private lateinit var profileAvatarImageView: ImageView
    private lateinit var profileNameTextView: TextView
    private lateinit var profileBioTextView: TextView
    private lateinit var profileReviewsRecyclerView: RecyclerView
    private lateinit var profileReviewsAdapter: ProfileReviewsAdapter
    private lateinit var settingsButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        profileAvatarImageView = view.findViewById(R.id.profile_avatar_image_view)
        profileNameTextView = view.findViewById(R.id.profile_name_text_view)
        profileBioTextView = view.findViewById(R.id.profile_bio_text_view)
        profileReviewsRecyclerView = view.findViewById(R.id.profile_reviews_recycler_view)
        settingsButton = view.findViewById(R.id.settings_button)
        return view
    }

    override fun onStart() {
        super.onStart()
        val app = activity?.application as BookHavenApplication
        val prefs = requireActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE)

        profileNameTextView.text = prefs.getString("user_name", "User Name")
        profileBioTextView.text = prefs.getString("user_bio", "No bio yet.")
        profileAvatarImageView.setImageResource(R.drawable.ic_default_avatar)

        val userReviews = app.bookDataManager.getReviews()
        profileReviewsAdapter = ProfileReviewsAdapter(userReviews)
        profileReviewsRecyclerView.adapter = profileReviewsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileReviewsRecyclerView.layoutManager = LinearLayoutManager(context)

        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}