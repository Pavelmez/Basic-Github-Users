
# Basic Github Users

Basic GitHub Users is an Android application built using Kotlin that allows users to explore GitHub users. The app provides the following features:
- View a list of GitHub users.
- Favorite a user.
- View favorite users.
- View detailed information about a user.
- View the followers and following of a user.

## Features

- **View GitHub Users**: Browse through a list of GitHub users.
- **Favorite a User**: Mark users as favorites and easily access them later.
- **Show Favorite Users**: View a list of users you have marked as favorites.
- **User Details**: Get detailed information about a user, including their repositories, bio, and more.
- **Followers and Following**: View the list of users that a particular user follows and is followed by.

## Installation

To run this project, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/github-user-explorer.git
   cd github-user-explorer
   ```

2. **Open in Android Studio**: Open the project in Android Studio.

3. **Build the project**: Let Gradle sync and build the project.

4. **Run the app**: Connect an Android device or start an emulator, then run the app.

## Usage

1. **Explore Users**: On the main screen, browse through the list of GitHub users.
2. **Favorite a User**: Tap on the star icon to favorite a user.
3. **View Favorites**: Access your favorite users from the favorites screen.
4. **User Details**: Tap on a user to view detailed information.
5. **Followers and Following**: Navigate to the followers or following tab to see who the user follows and who follows them.

## Development

### Prerequisites

- Android Studio
- Kotlin
- GitHub API Key (You can obtain it from [GitHub Developer Settings](https://github.com/settings/developers))

### Configuration

1. **API Key**: Add your GitHub API key to the `local.properties` file:
   ```properties
   githubApiKey=your_github_api_key
   ```

2. **Dependencies**: The project uses several libraries, including:
   - Retrofit for API calls
   - Glide for image loading
   - Room for local database
   - ViewModel and LiveData for MVVM architecture

### Project Structure

- `api` - Contains the Retrofit API service and API response models.
- `data` - Contains the repository pattern for data handling.
- `db` - Contains the Room database and DAO interfaces.
- `ui` - Contains the UI components like Activities, Fragments, and ViewModels.
- `utils` - Utility classes and helper functions.

### Contributing

Feel free to contribute to this project by submitting a pull request. Please follow the standard GitHub flow:
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push to the branch.
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [GitHub API](https://developer.github.com/v3/)
- [Retrofit](https://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)
- [Room](https://developer.android.com/training/data-storage/room)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)

---

Feel free to customize this README further based on the specifics of your project, including adding any additional details or instructions as needed.
